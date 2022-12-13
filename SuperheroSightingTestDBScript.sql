DROP DATABASE IF EXISTS SuperheroSightingTestDBScript;
CREATE DATABASE SuperheroSightingTestDBScript;
USE SuperheroSightingTestDBScript;

-- Creates tables using Second Normal form and Primary Keys

CREATE TABLE Superpowers(
  superpowerID INT PRIMARY KEY AUTO_INCREMENT,
  superpowerName VARCHAR(60) NOT NULL,
  superpowerDescription VARCHAR(255) NOT NULL
);


CREATE TABLE Heroes(
  heroID INT PRIMARY KEY AUTO_INCREMENT,
  isHero BOOLEAN NOT NULL,
  heroName VARCHAR(60) NOT NULL UNIQUE,
  heroDescription VARCHAR(255) NOT NULL
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
  heroID INT NOT NULL,
  locationID INT NOT NULL,
    FOREIGN KEY (heroID) 
    REFERENCES Heroes(heroID),
    FOREIGN KEY (locationID) 
    REFERENCES Locations(locationID)
);


CREATE TABLE Organizations(
  organizationID INT PRIMARY KEY AUTO_INCREMENT, 
  organizationName VARCHAR(60) NOT NULL,
  isHero BOOLEAN NOT NULL,
  organizationDescription VARCHAR(255) NOT NULL,
  organizationAddress VARCHAR(255),
  organizationContact VARCHAR(60) NOT NULL,
  locationID INT NULL,
    FOREIGN KEY (locationID) 
    REFERENCES Locations(locationID)
);


-- Relationship between tables

CREATE TABLE HeroSuperpowers(
  heroID INT NOT NULL,
  superpowerID INT NOT NULL,
  CONSTRAINT PK_Superpower 
  PRIMARY KEY (heroID, superpowerID),
    FOREIGN KEY (heroID) 
    REFERENCES Heroes(heroID),
    FOREIGN KEY (superpowerID) 
    REFERENCES Superpowers(superpowerID)
);


CREATE TABLE HeroSightings (
  heroID INT NOT NULL,
  sightingID INT NOT NULL,
  PRIMARY KEY (heroID, sightingID),
  CONSTRAINT PK_HeroSighting
    FOREIGN KEY (heroID)
    REFERENCES Heroes(heroID),
    FOREIGN KEY (sightingID)
    REFERENCES Sightings(sightingID)
);


CREATE TABLE HeroOrganizations(
  heroID INT NOT NULL,
  organizationID INT NOT NULL,
  PRIMARY KEY (heroID, organizationID),
  CONSTRAINT PK_HeroOrganization
    FOREIGN KEY (heroID) 
    REFERENCES Heroes(heroID),
    FOREIGN KEY (organizationID) 
    REFERENCES Organizations(organizationID)
);


