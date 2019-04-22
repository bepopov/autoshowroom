CREATE DATABASE autoshowroom;
GO
USE autoshowroom;
GO
CREATE LOGIN autoshowlogin WITH PASSWORD = autopassword;
GO
CREATE USER autoshowlogin FOR LOGIN autoshowlogin;
GO
ALTER SERVER ROLE sysadmin ADD MEMBER [autoshowlogin];
GO