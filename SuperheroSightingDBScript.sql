DROP DATABASE IF EXISTS superheroSightingDB;
CREATE DATABASE superheroSightingDB;
USE superheroSightingDB;

--Creates tables using Second Normal form and Primary Keys

CREATE TABLE Powers(
	powerID INT PRIMARY KEY AUTO_INCREMENT,
  powerName VARCHAR(60) NOT NULL,
  powerDescription VARCHAR(255) NOT NULL
);


CREATE TABLE Supers(
	superID INT PRIMARY KEY AUTO_INCREMENT,
  superName VARCHAR(60) NOT NULL UNIQUE,
  superDescription VARCHAR(255) NOT NULL
);


CREATE TABLE Locations(
	locationID INT PRIMARY KEY AUTO_INCREMENT,
  locationName VARCHAR(60) NOT NULL,
  locationDescription VARCHAR(255) NOT NULL,
  locationAddress VARCHAR(60) NOT NULL,
  locationLatitude VARCHAR(60), 
  locationLongitude VARCHAR(60)
);


CREATE TABLE Sightings(
	sightingID INT PRIMARY KEY AUTO_INCREMENT,
  sightingDate DATE NOT NULL,
  superID INT NOT NULL,
  locationID INT NOT NULL,
    FOREIGN KEY (superID) 
    REFERENCES Supers(superID),
    FOREIGN KEY (locationID) 
    REFERENCES Locations(locationID)
);


CREATE TABLE Organizations(
	organizationID INT PRIMARY KEY AUTO_INCREMENT, 
  organizationName VARCHAR(60) NOT NULL,
  organizationDescription VARCHAR(255) NOT NULL,
  organizationContact VARCHAR(60) NOT NULL,
  locationID INT NULL,
    FOREIGN KEY (locationID) 
    REFERENCES Locations(locationID)
);


--Relationship between tables

CREATE TABLE Superpowers(
	superID INT NOT NULL,
  powerID INT NOT NULL,
  CONSTRAINT PK_Superpower 
  PRIMARY KEY (superID, powerID),
    FOREIGN KEY (superID) 
    REFERENCES Supers(superID),
    FOREIGN KEY (powerID) 
    REFERENCES Powers(powerID)
);


CREATE TABLE SuperSighting(
  	superID INT NOT NULL,
  sightingID INT NOT NULL,
  PRIMARY KEY (superID, sightingID),
  CONSTRAINT PK_SuperSighting
    FOREIGN KEY (superID)
    REFERENCES Supers(superID),
    FOREIGN KEY (sightingID)
    REFERENCES Sightings(sightingID)
);


CREATE TABLE SuperOrganizations(
	superID INT NOT NULL,
  organizationID INT NOT NULL,
  PRIMARY KEY (superID, organizationID),
  CONSTRAINT PK_SuperOrganization
    FOREIGN KEY (superID) 
    REFERENCES Supers(superID),
    FOREIGN KEY (organizationID) 
    REFERENCES Organizations(organizationID)
);

