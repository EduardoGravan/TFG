"""
Este es el módulo para horarios que contiene todas las llamadas ReST relacionadas
con las tablas de Schedule/Attendance en la base de datos
"""

# Modulos de flask
from flask import make_response, abort
# Base de datos
import sqlite3
# JSON
import json
# Biblioteca con las funciones de tiempo
import time_library

def create_schedule(schedule):
    """
    Esta función intenta crear un nuevo registro de horario en la base de datos

    :param employee:    object que contiene toda la información necesaria para crear el horario
    :return:            201 si correcto, 400 si no existe el empleado, 409 si el horario ya existe
    """
    email = schedule.get("email", None)
    date = schedule.get("date", None)
    start_time = schedule.get("start_time", None)
    end_time = schedule.get("end_time", None)
    hours = time_library.hour_difference(start_time, end_time)

    try:
        con = sqlite3.connect("./Database/DB.db")
        cursor = con.cursor()

        # Se activan las foreign keys para la consulta, evitando que se pueda insertar el 
        # registro de Schedule si no existe un empleado con ese email
        cursor.execute("PRAGMA foreign_keys=1;")
        # Se intentan crear los registros de horario y asistencia
        cursor.execute(f"INSERT INTO Schedule VALUES(\'{email}\',\'{date}\',\'{start_time}\',\'{end_time}\',\'{hours}\');")
        cursor.execute(f"INSERT INTO Attendance VALUES(\'{email}\',\'{date}\', null, null, null);")
        con.commit()

        cursor.close()
        con.close()

        return make_response("Inserted new schedule record successfuly", 201)

    except Exception as e:
        if "FOREIGN" in str(e):
            abort(400, "Employee doesn't exist in the database")
        else:
            abort(409, "The inserted schedule already exists in the database")

def delete_schedule(email, date):
    """
    Esta función intenta borrar un registro de horario de la base de datos

    :param email:       string con el email del empleado
    :param date:        string con la fecha del horario
    :return:            204 si correcto, 400 si hay error
    """    
    con = sqlite3.connect("./Database/DB.db")
    cursor = con.cursor()

    # Se intentan borrar los registros de horario y asistencia
    cursor.execute(f"DELETE FROM Schedule WHERE email=\'{email}\' AND date=\'{date}\';")
    cursor.execute(f"DELETE FROM Attendance WHERE email=\'{email}\' AND date=\'{date}\';")
    con.commit()

    rows = cursor.rowcount

    cursor.close()
    con.close()

    if rows == 1:
        make_response("Deleted schedule record successfuly", 204)
    else:
        abort(400, "No schedule record for that employee on that day")

def get_schedule_info(email, month, year):
    """
    Esta función recupera todos los registros de horario para un empleado

    :param email:   email del empleado
    :return:        json con toda la información de los horarios
    """
    
    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()

    cursor.execute(f"SELECT * FROM Schedule WHERE email=\'{email}\' AND date BETWEEN \"{year}-{month}-01\" AND \"{year}-{month}-31\";")

    result = cursor.fetchall()
    cursor.close()
    con.close()

    if result:
        resultJson = json.dumps([dict(i) for i in result], indent=1)
        return make_response(resultJson, 200)
    else:
        abort(400, "No data for specified parameters")

def get_specific_schedule_info(email, date):
    """
    Esta función devuelve la información de horario de un empleado para un día especificado

    :param email:       string con el email del empleado
    :param date:        string con la fecha del horario
    :return:            json con los datos del horario
    """

    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()

    cursor.execute(f"SELECT * FROM Schedule WHERE email=\'{email}\' AND date=\'{date}\';")

    result = cursor.fetchall()
    cursor.close()
    con.close()

    resultJson = json.dumps([dict(i) for i in result], indent=1)
    return make_response(resultJson, 200)

def get_attendance_info(email):
    """
    Esta función recupera todos los registros de asistencia de un empleado

    :param email:   email del empleado
    :return:        json con la información de asistencia
    """
    current_date = time_library.get_timestamp().split(" ")[1]
    
    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()


    cursor.execute(f"SELECT email, date, arrived_time, left_time FROM Attendance WHERE email=\'{email}\' AND date <= \"{current_date}\";")

    result = cursor.fetchall()
    cursor.close()
    con.close()

    if result:
        resultJson = json.dumps([dict(i) for i in result], indent=1)
        return make_response(resultJson, 200)
    else:
        abort(400, "No data for specified parameters")

def get_specific_attendance_info(email, month, year):
    """
    Esta función recupera la información de asistencia para un empleado en un día especificado

    :param email:       string con el email del empleado
    :param date:        string con la fecha del registro de asistencia
    :return:            json con los datos del registro de asistencia
    """
    current_date = time_library.get_timestamp().split(" ")[1]

    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()

    cursor.execute(f"SELECT email, date, arrived_time, left_time FROM Attendance WHERE email=\'{email}\' AND date BETWEEN \"{year}-{month}-01\" AND \"{year}-{month}-31\";")

    result = cursor.fetchall()
    cursor.close()
    con.close()

    if result:
        resultJson = json.dumps([dict(i) for i in result], indent=1)
        return make_response(resultJson, 200)
    else:
        abort(400, "No data for specified parameters")

def create_attendance_record(email):
    """
    Esta función crea un registro de asistencia para un empleado

    :param email:       string con el email del empleado
    :return:            string que especifica que cambios se han hecho en la base de datos
    """
    timestamp = time_library.get_timestamp().split(" ")
    response_code = 400
    response_str = ""

    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()

    # Recupera la información de asistencia del empleado para el día de hoy 
    cursor.execute(f"SELECT * FROM Attendance WHERE email=\'{email}\' AND date=\'{timestamp[1]}\';")
    result = cursor.fetchone()

    # Si el registro de asistencia existe
    if result:
        # Si el empleado no ha registrado su llegada aún, se actualiza la hora de llegada
        if result['arrived_time'] is None:
            cursor.execute(f"UPDATE Attendance SET arrived_time=\'{timestamp[0]}\' WHERE email=\'{email}\' AND date=\'{timestamp[1]}\';")
            response_str = "arrived_time updated"
            response_code = 200

        # En caso contrario, si ya se ha registrado la llegada pero no se ha registrado la salida y
        # ha pasado más de un minuto desde el registro anterior, se registra la hora de salida
        elif result['left_time'] is None:
            if time_library.valid_time_difference(result['arrived_time'], timestamp[0]):
                hours_worked = time_library.hour_difference(result['arrived_time'], timestamp[0])
                cursor.execute(f"UPDATE Attendance SET left_time=\'{timestamp[0]}\', hours_worked={hours_worked} WHERE email=\'{email}\' AND date=\'{timestamp[1]}\';")
                response_str = "left_time updated"
                response_code = 200
            else:
                response_str = "update too soon"

        # En el caso de que tanto la hora de llegada como la de salida ya tengan datos, no se puede
        # actualizar nada más en al base de datos y por lo tanto la consulta es un error
        else:
            response_str = "table already updated"

    # Si no hay ningún registro de asistencia para el día de hoy para este empleado, la consulta es un error
    else:
        response_str = "no data to update"

    con.commit()
    cursor.close()
    con.close()

    if response_code == 200:
        return make_response(response_str, 200)
    else:
        return make_response(response_str, 400)


def get_analysis(email, month, year):
    """
    Esta función recupera datos de análisis sobre las horas trabajadas por un empleado
    en un determinado mes
    
    :param email:       string con el email del empleado
    :param month:       string con el mes a analizar
    :param year:        string con el año a analizar
    :return:            json con la información de análisis
    """

    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()

    cursor.execute(f"""SELECT sum(hours) AS total_hours, sum(hours_worked) AS hours_worked, sum(case when hours_worked is null then 1 else 0 end) as absences
        FROM Attendance INNER JOIN Schedule ON Attendance.date=Schedule.date 
        WHERE Attendance.email=\'{email}\' AND Attendance.date BETWEEN \"{year}-{month}-01\" AND \"{year}-{month}-31\";""")

    result = cursor.fetchall()

    if result:
        resultJson = json.dumps([dict(i) for i in result], indent=1)
        return make_response(resultJson, 200)
    else:
        abort(400, "No data for specified parameters")
