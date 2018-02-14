CREATE TABLE `artboard` (
  `ArtboardID` int(11) NOT NULL AUTO_INCREMENT,
  `Artboard_Name` varchar(15) DEFAULT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`ArtboardID`),
  UNIQUE KEY `artboard_ArtboardName_uindex` (`Artboard_Name`),
  KEY `UserID_idx` (`UserID`),
  CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16;

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(15) NOT NULL,
  `Hash` varchar(128) NOT NULL,
  `Salt` varchar(8) NOT NULL,
  `Admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `users_Username_uindex` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf16;
