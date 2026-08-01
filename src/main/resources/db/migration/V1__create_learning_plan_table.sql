CREATE TABLE learning_plan (
    id BIGINT GENERATED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    habilidade VARCHAR(150) NOT NULL,
    nivel VARCHAR(50) NOT NULL,
    dias_disponiveis INTEGER NOT NULL,
    horas_por_dia INTEGER NOT NULL,
    objetivo VARCHAR(500)
);