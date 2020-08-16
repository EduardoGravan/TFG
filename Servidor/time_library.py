"""
Librería propia que recoge las funciones de tiempo usadas a la hora de hacer cálculos
sobre timestamps o inserciones en la base de datos.
"""

# Módulos del sistema
from datetime import datetime, timedelta

def get_timestamp():
    """
    Función que devuelve el timestamp actual

    :return:    timestamp en formato ("%H:%M:%S %Y-%m-%d")
    """
    return datetime.now().strftime(("%H:%M:%S %Y-%m-%d"))

def valid_time_difference(t1, t2):
    """
    Función para comprobar si la diferencia de tiempo es válida (> 1 minuto)

    :param t1:  primer timestamp
    :param t2:  segundo timestamp
    :return:    true si ha pasado más de 1 minuto, false si no
    """
    difference = datetime.strptime(t2, "%H:%M:%S") - datetime.strptime(t1, "%H:%M:%S")

    return difference > timedelta(minutes=1)

def hour_difference(t1, t2):
    """
    Función que devuelve la diferencia en horas entre dos timestamps
    
    :param t1:  primer timestamp
    :param t2:  segundo timestamp
    :return:    int con las horas de diferencia entre t1 y t2
    """
    float_hour = (datetime.strptime(t2, "%H:%M:%S") - datetime.strptime(t1, "%H:%M:%S")).seconds / 3600
    int_hour = (datetime.strptime(t2, "%H:%M:%S") - datetime.strptime(t1, "%H:%M:%S")).seconds // 3600

    # Se considera una hora completa trabajada si han pasado al menos 50 minutos (0.83)
    if (float_hour - int_hour) > 0.83:
        return int_hour + 1
    else:
        return int_hour
