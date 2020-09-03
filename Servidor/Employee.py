"""
Este es el módulo para empleados que contiene todas las llamadas ReST relacionadas
con la tabla Employee en la base de datos
"""

# Modulos de flask
from flask import make_response, abort
# Base de datos
import sqlite3
# JSON
import json
# Biblioteca con las funciones de tiempo
import time_library


def login(employee):
    """
    Esta función comprueba el nombre de usuario y contraseña proporcionados por el usuario
    e intenta recuperar todos los datos del empleado si los datos proporcionados coinciden con los de la base de datos
    
    :param employee:    objeto que contiene el nombre de usuario y contraseña
    :return:            200 si correcto, 400 si hay error
    """
    username = employee.get("username", None)
    password = employee.get("password", None)

    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()

    cursor.execute(f"SELECT email, name, is_admin FROM Employee WHERE email=\'{username}\' AND password=\'{password}\'")

    result = cursor.fetchall()
    cursor.close()
    con.close()

    if result:
        resultJson = json.dumps([dict(i) for i in result], indent=1)
        return make_response(resultJson, 200)
    else:
        abort(401, "Unsuccessful login")


def read_all():
    """
    Esta función devuelve la lista completa de empleados

    :return:        json con toda la lista de empleados
    """
    
    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()

    cursor.execute("SELECT email, name, is_admin FROM Employee;")

    result = cursor.fetchall()
    cursor.close()
    con.close()

    resultJson = json.dumps([dict(i) for i in result], indent=1)
    return make_response(resultJson, 200)


def create_employee(employee):
    """
    Esta función intenta crear un nuevo empleado en la base de datos

    :param employee:    objeto que contiene toda la información necesaria para crear el empleado
    :return:            201 si correcto, 400 si hay error
    """
    email = employee.get("email", None)
    password = employee.get("password", None)
    name = employee.get("name", None)
    is_admin = 1 if employee.get("is_admin", False) else 0

    try:
        con = sqlite3.connect("./Database/DB.db")
        cursor = con.cursor()

        # Se intenta insertar el nuevo empleado
        cursor.execute(f"INSERT INTO Employee VALUES(\'{email}\',\'{name}\',\'{password}\',{is_admin});")
        con.commit()

        cursor.close()
        con.close()

        return make_response("Inserted new employee successfuly", 201)

    except:
        abort(400, "The inserted employee already exists in the database")

def delete_employee(email):
    """
    Esta función intenta borrar a un empelado de la base de datos

    :param email:       string con el email del empleado
    :return:            204 si correcto, 400 si hay error
    """

    con = sqlite3.connect("./Database/DB.db")
    cursor = con.cursor()

    # Se intenta borrar al empleado
    cursor.execute(f"DELETE FROM Employee WHERE email=\'{email}\';")
    con.commit()

    rows = cursor.rowcount

    cursor.close()
    con.close()

    if rows == 1:
        make_response("Deleted employee successfuly", 204)
    else:
        abort(400, "No employee exists with provided email")

def read_name(name):
    """
    Esta función recupera todos los empleados cuyo nombre se corresponde con el parámetro

    :param name:        string con el nombre a buscar en la base de datos
    :return:            200 si correcto y json con la información de los empleados, 400 si hay error
    """
    con = sqlite3.connect("./Database/DB.db")
    con.row_factory = sqlite3.Row
    cursor = con.cursor()

    # Búsqueda de los empleados
    cursor.execute(f"SELECT email, name, is_admin FROM Employee WHERE name LIKE \'%{name}%\';")

    result = cursor.fetchall()
    cursor.close()
    con.close()

    if result:
        resultJson = json.dumps([dict(i) for i in result], indent=1)
        return make_response(resultJson, 200)
    else:
        abort(400, "No employees with that name")

