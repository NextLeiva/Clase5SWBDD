Feature: Funcionalidad Gestion de usuario

  @create
  Scenario: Crear un usuario para la petStore
    Given el owner con los siguientes detalles:
      | id | username | firstName | lastName | email           | password | phone     | userStatus |
      | 5  | Erick    | Montes    | Morales  | erick@gmail.com | 123456   | 969929157 | 0          |
    When el owner registra el usuario
    Then el usuario se debe visualizar en la lista con su nombre Erick

  @update
  Scenario: Actualizar el usuario en la petStore
    Given el owner con los siguientes detalles:
      | id | username | firstName | lastName | email           | password | phone     | userStatus |
      | 5  | Juan     | Morales   | Morales  | erick@gmail.com | 123456   | 969929146 | 0          |
    When el owner actualiza el usuario Erick
    Then el usuario se debe visualizar en la lista con su nombre Juan

  @delete
  Scenario: Eliminar el usuario en la petStore
    Given el owner con los siguientes detalles:
      | id | username | firstName | lastName | email           | password | phone     | userStatus |
      | 5  | Erick    | Montes    | Morales  | erick@gmail.com | 123456   | 969929157 | 0          |
    And el owner registra el usuario
    When el owner realiza la eliminacion del usuario
    Then el owner obtiene en la busqueda el mensaje User not found
