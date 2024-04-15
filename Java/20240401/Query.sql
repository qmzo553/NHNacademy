DELIMITER $$
CREATE PROCEURE getAircraft
(
    m_aircraftno int
)
BEGIN
    SELECT *
    FROM aircraft
    WHERE AircraftNo = m_aircraftno;

END $$
DELIMITER;