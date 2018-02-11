create table Users(
UserID char (15) NOT NULL,
Username varchar (15) NOT NULL,
Hash nvarchar(128) NOT NULL,
Salt nvarchar(128) NOT NULL,
Admin bool NOT NULL,
primary key (UserID)
);

create table ArtBoard(
UserID char (15) NOT NULL references Users(UserID),
ArtboardID char (15) NOT NULL,
ArtboardName varchar (15),
primary key (ArtboardID)
);

