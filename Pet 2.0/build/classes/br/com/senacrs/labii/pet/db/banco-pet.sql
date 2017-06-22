CREATE TABLE owners(

    codOwner SERIAL NOT NULL PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL,
    nameOwner VARCHAR(100) NULL,
    email VARCHAR(100) NOT NULL
    
);

CREATE TABLE animals(

    codAnimal SERIAL NOT NULL PRIMARY KEY,
    typeAnimal VARCHAR(25) NOT NULL,
    race VARCHAR(25) NOT NULL,
    nameAnimal VARCHAR(30) NOT NULL,
    codOwner INTEGER REFERENCES owners (codOwner)
    
);

CREATE TABLE procedures(

    codProc SERIAL NOT NULL PRIMARY KEY,
    proc VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL
    
);

CREATE TABLE schedules(

	codSched SERIAL NOT NULL PRIMARY KEY,
	codAnimal INTEGER REFERENCES animals (codAnimal),
	codOwner INTEGER REFERENCES owners (codOwner),
	date DATE NOT NULL,
	schedule DATE NOT NULL,
	totalPrice FLOAT NOT NULL

);

CREATE TABLE SchedProcs (

	codProc INTEGER REFERENCES procedures (codProc),
	codSched INTEGER REFERENCES schedules (codSched)

);