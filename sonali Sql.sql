USE [master]
GO
/****** Object:  Database [DeskBookDev]    Script Date: 6/7/2023 3:57:25 PM ******/
CREATE DATABASE [DeskBookDev]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DeskBookDev', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\DeskBookDev.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DeskBookDev_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\DeskBookDev_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [DeskBookDev] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DeskBookDev].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DeskBookDev] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DeskBookDev] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DeskBookDev] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DeskBookDev] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DeskBookDev] SET ARITHABORT OFF 
GO
ALTER DATABASE [DeskBookDev] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DeskBookDev] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DeskBookDev] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DeskBookDev] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DeskBookDev] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DeskBookDev] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DeskBookDev] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DeskBookDev] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DeskBookDev] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DeskBookDev] SET  ENABLE_BROKER 
GO
ALTER DATABASE [DeskBookDev] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DeskBookDev] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DeskBookDev] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DeskBookDev] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DeskBookDev] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DeskBookDev] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DeskBookDev] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DeskBookDev] SET RECOVERY FULL 
GO
ALTER DATABASE [DeskBookDev] SET  MULTI_USER 
GO
ALTER DATABASE [DeskBookDev] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DeskBookDev] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DeskBookDev] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DeskBookDev] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DeskBookDev] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DeskBookDev] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'DeskBookDev', N'ON'
GO
ALTER DATABASE [DeskBookDev] SET QUERY_STORE = OFF
GO
USE [DeskBookDev]
GO
/****** Object:  Schema [Booking]    Script Date: 6/7/2023 3:57:26 PM ******/
CREATE SCHEMA [Booking]
GO
/****** Object:  Schema [Ref]    Script Date: 6/7/2023 3:57:26 PM ******/
CREATE SCHEMA [Ref]
GO
/****** Object:  Table [Booking].[SeatRequest]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Booking].[SeatRequest](
	[SeatRequestId] [int] IDENTITY(1,1) NOT NULL,
	[EmployeeId] [nvarchar](50) NOT NULL,
	[SeatId] [int] NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[BookingDate] [date] NOT NULL,
	[Reason] [varchar](200) NOT NULL,
	[RequestStatus] [tinyint] NOT NULL,
	[ModifiedDate] [datetime] NULL,
	[ModifiedBy] [nvarchar](50) NULL,
	[DeletedDate] [datetime] NULL,
 CONSTRAINT [PK_SeatRequest_SeatRequestId] PRIMARY KEY CLUSTERED 
(
	[SeatRequestId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[EmployeeId] [nvarchar](50) NOT NULL,
	[EmailId] [varchar](80) NOT NULL,
	[FirstName] [varchar](100) NOT NULL,
	[LastName] [varchar](100) NOT NULL,
	[PhoneNumber] [varchar](15) NULL,
	[ModeOfWorkId] [tinyint] NULL,
	[ProfilePictureFileName] [varchar](60) NULL,
	[ProfilePictureFilePath] [varchar](max) NULL,
	[DesignationId] [tinyint] NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
	[ModifiedDate] [datetime] NULL,
	[ModifiedBy] [nvarchar](50) NULL,
	[IsActive] [bit] NOT NULL,
	[DeletedDate] [datetime] NULL,
	[DeletedBy] [nvarchar](50) NULL,
 CONSTRAINT [PK_Employee_EmployeeId] PRIMARY KEY CLUSTERED 
(
	[EmployeeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_Employee_EmailId] UNIQUE NONCLUSTERED 
(
	[EmailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EmployeeWorkingDays]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EmployeeWorkingDays](
	[EmployeeWorkingDayId] [int] IDENTITY(1,1) NOT NULL,
	[EmployeeId] [nvarchar](50) NOT NULL,
	[WorkingDayId] [tinyint] NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
	[ModifiedDate] [datetime] NULL,
	[ModifiedBy] [nvarchar](50) NULL,
	[DeletedDate] [datetime] NULL,
	[DeletedBy] [nvarchar](50) NULL,
 CONSTRAINT [PK_EmployeeWorkingDays_EmployeeWorkingDayId] PRIMARY KEY CLUSTERED 
(
	[EmployeeWorkingDayId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SeatConfiguration]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SeatConfiguration](
	[SeatConfigurationId] [int] IDENTITY(1,1) NOT NULL,
	[SeatId] [int] NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
	[ModifiedDate] [datetime] NULL,
	[ModifiedBy] [nvarchar](50) NULL,
	[EmployeeId] [nvarchar](50) NOT NULL,
	[DeletedDate] [datetime] NULL,
	[DeletedBy] [nvarchar](50) NULL,
 CONSTRAINT [PK_SeatConfiguration_SeatConfigurationId] PRIMARY KEY CLUSTERED 
(
	[SeatConfigurationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Ref].[City]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Ref].[City](
	[CityId] [int] IDENTITY(1,1) NOT NULL,
	[CityName] [varchar](10) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_City_CityId] PRIMARY KEY CLUSTERED 
(
	[CityId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Ref].[Column]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Ref].[Column](
	[ColumnId] [tinyint] IDENTITY(1,1) NOT NULL,
	[ColumnName] [varchar](5) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
	[FloorId] [tinyint] NOT NULL,
 CONSTRAINT [PK_Column_ColumnId] PRIMARY KEY CLUSTERED 
(
	[ColumnId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Ref].[Designation]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Ref].[Designation](
	[DesignationId] [tinyint] IDENTITY(1,1) NOT NULL,
	[DesignationName] [varchar](10) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Designation_DesignationId] PRIMARY KEY CLUSTERED 
(
	[DesignationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Ref].[Floor]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Ref].[Floor](
	[FloorId] [tinyint] IDENTITY(1,1) NOT NULL,
	[FloorName] [varchar](15) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
	[CityId] [int] NOT NULL,
 CONSTRAINT [PK_Floor_FloorId] PRIMARY KEY CLUSTERED 
(
	[FloorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Ref].[ModeOfWork]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Ref].[ModeOfWork](
	[ModeOfWorkId] [tinyint] IDENTITY(1,1) NOT NULL,
	[ModeOfWork] [varchar](20) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_ModeOfWork_ModeOfWorkId] PRIMARY KEY CLUSTERED 
(
	[ModeOfWorkId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Ref].[Seat]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Ref].[Seat](
	[SeatId] [int] IDENTITY(1,1) NOT NULL,
	[SeatNumber] [tinyint] NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
	[ColumnId] [tinyint] NOT NULL,
	[IsAvailable] [bit] NOT NULL,
	[ModifiedDate] [datetime] NULL,
	[ModifiedBy] [nvarchar](50) NULL,
 CONSTRAINT [PK_Seat_SeatId] PRIMARY KEY CLUSTERED 
(
	[SeatId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Ref].[WorkingDay]    Script Date: 6/7/2023 3:57:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Ref].[WorkingDay](
	[WorkingDayId] [tinyint] IDENTITY(1,1) NOT NULL,
	[Day] [varchar](10) NOT NULL,
	[CreatedDate] [datetime] NOT NULL,
	[CreatedBy] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_WorkingDay_WorkingDayId] PRIMARY KEY CLUSTERED 
(
	[WorkingDayId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [Booking].[SeatRequest] ADD  CONSTRAINT [DF_SeatRequest_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[Employee] ADD  CONSTRAINT [DF_Employee_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[Employee] ADD  CONSTRAINT [DF_Employee_IsActive]  DEFAULT ('1') FOR [IsActive]
GO
ALTER TABLE [dbo].[EmployeeWorkingDays] ADD  CONSTRAINT [DF_EmployeeWorkingDays_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [dbo].[SeatConfiguration] ADD  CONSTRAINT [DF_SeatConfiguration_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [Ref].[City] ADD  CONSTRAINT [DF_City_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [Ref].[Column] ADD  CONSTRAINT [DF_Column_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [Ref].[Designation] ADD  CONSTRAINT [DF_Designation_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [Ref].[Floor] ADD  CONSTRAINT [DF_Floor_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [Ref].[ModeOfWork] ADD  CONSTRAINT [DF_ModeOfWork_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [Ref].[Seat] ADD  CONSTRAINT [DF_Seat_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [Ref].[Seat] ADD  CONSTRAINT [DF_Seat_IsAvailable]  DEFAULT ('1') FOR [IsAvailable]
GO
ALTER TABLE [Ref].[WorkingDay] ADD  CONSTRAINT [DF_WorkingDay_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
GO
ALTER TABLE [Booking].[SeatRequest]  WITH CHECK ADD  CONSTRAINT [FK_SeatRequest_EmployeeId] FOREIGN KEY([EmployeeId])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Booking].[SeatRequest] CHECK CONSTRAINT [FK_SeatRequest_EmployeeId]
GO
ALTER TABLE [Booking].[SeatRequest]  WITH CHECK ADD  CONSTRAINT [FK_SeatRequest_ModifiedBy] FOREIGN KEY([ModifiedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Booking].[SeatRequest] CHECK CONSTRAINT [FK_SeatRequest_ModifiedBy]
GO
ALTER TABLE [Booking].[SeatRequest]  WITH CHECK ADD  CONSTRAINT [FK_SeatRequest_SeatId] FOREIGN KEY([SeatId])
REFERENCES [Ref].[Seat] ([SeatId])
GO
ALTER TABLE [Booking].[SeatRequest] CHECK CONSTRAINT [FK_SeatRequest_SeatId]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_CreatedBy]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_DeletedBy] FOREIGN KEY([DeletedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_DeletedBy]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Designation] FOREIGN KEY([DesignationId])
REFERENCES [Ref].[Designation] ([DesignationId])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Designation]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_ModeOfWork] FOREIGN KEY([ModeOfWorkId])
REFERENCES [Ref].[ModeOfWork] ([ModeOfWorkId])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_ModeOfWork]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_ModifiedBy] FOREIGN KEY([ModifiedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_ModifiedBy]
GO
ALTER TABLE [dbo].[EmployeeWorkingDays]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeWorkingDays_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[EmployeeWorkingDays] CHECK CONSTRAINT [FK_EmployeeWorkingDays_CreatedBy]
GO
ALTER TABLE [dbo].[EmployeeWorkingDays]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeWorkingDays_DeletedBy] FOREIGN KEY([DeletedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[EmployeeWorkingDays] CHECK CONSTRAINT [FK_EmployeeWorkingDays_DeletedBy]
GO
ALTER TABLE [dbo].[EmployeeWorkingDays]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeWorkingDays_Employee] FOREIGN KEY([EmployeeId])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[EmployeeWorkingDays] CHECK CONSTRAINT [FK_EmployeeWorkingDays_Employee]
GO
ALTER TABLE [dbo].[EmployeeWorkingDays]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeWorkingDays_ModifiedBy] FOREIGN KEY([ModifiedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[EmployeeWorkingDays] CHECK CONSTRAINT [FK_EmployeeWorkingDays_ModifiedBy]
GO
ALTER TABLE [dbo].[EmployeeWorkingDays]  WITH CHECK ADD  CONSTRAINT [FK_EmployeeWorkingDays_WorkingDay] FOREIGN KEY([WorkingDayId])
REFERENCES [Ref].[WorkingDay] ([WorkingDayId])
GO
ALTER TABLE [dbo].[EmployeeWorkingDays] CHECK CONSTRAINT [FK_EmployeeWorkingDays_WorkingDay]
GO
ALTER TABLE [dbo].[SeatConfiguration]  WITH CHECK ADD  CONSTRAINT [FK_ SeatConfiguration_Employee] FOREIGN KEY([EmployeeId])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[SeatConfiguration] CHECK CONSTRAINT [FK_ SeatConfiguration_Employee]
GO
ALTER TABLE [dbo].[SeatConfiguration]  WITH CHECK ADD  CONSTRAINT [FK_SeatConfiguration_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[SeatConfiguration] CHECK CONSTRAINT [FK_SeatConfiguration_CreatedBy]
GO
ALTER TABLE [dbo].[SeatConfiguration]  WITH CHECK ADD  CONSTRAINT [FK_SeatConfiguration_DeletedBy] FOREIGN KEY([DeletedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[SeatConfiguration] CHECK CONSTRAINT [FK_SeatConfiguration_DeletedBy]
GO
ALTER TABLE [dbo].[SeatConfiguration]  WITH CHECK ADD  CONSTRAINT [FK_SeatConfiguration_ModifiedBy] FOREIGN KEY([ModifiedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [dbo].[SeatConfiguration] CHECK CONSTRAINT [FK_SeatConfiguration_ModifiedBy]
GO
ALTER TABLE [dbo].[SeatConfiguration]  WITH CHECK ADD  CONSTRAINT [FK_SeatConfiguration_Seat] FOREIGN KEY([SeatId])
REFERENCES [Ref].[Seat] ([SeatId])
GO
ALTER TABLE [dbo].[SeatConfiguration] CHECK CONSTRAINT [FK_SeatConfiguration_Seat]
GO
ALTER TABLE [Ref].[City]  WITH CHECK ADD  CONSTRAINT [FK_City_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Ref].[City] CHECK CONSTRAINT [FK_City_CreatedBy]
GO
ALTER TABLE [Ref].[Column]  WITH CHECK ADD  CONSTRAINT [FK_Column_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Ref].[Column] CHECK CONSTRAINT [FK_Column_CreatedBy]
GO
ALTER TABLE [Ref].[Column]  WITH CHECK ADD  CONSTRAINT [FK_Column_Floor] FOREIGN KEY([FloorId])
REFERENCES [Ref].[Floor] ([FloorId])
GO
ALTER TABLE [Ref].[Column] CHECK CONSTRAINT [FK_Column_Floor]
GO
ALTER TABLE [Ref].[Designation]  WITH CHECK ADD  CONSTRAINT [FK_Designation_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Ref].[Designation] CHECK CONSTRAINT [FK_Designation_CreatedBy]
GO
ALTER TABLE [Ref].[Floor]  WITH CHECK ADD  CONSTRAINT [FK_Floor_City] FOREIGN KEY([CityId])
REFERENCES [Ref].[City] ([CityId])
GO
ALTER TABLE [Ref].[Floor] CHECK CONSTRAINT [FK_Floor_City]
GO
ALTER TABLE [Ref].[Floor]  WITH CHECK ADD  CONSTRAINT [FK_Floor_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Ref].[Floor] CHECK CONSTRAINT [FK_Floor_CreatedBy]
GO
ALTER TABLE [Ref].[ModeOfWork]  WITH CHECK ADD  CONSTRAINT [FK_ModeOfWork_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Ref].[ModeOfWork] CHECK CONSTRAINT [FK_ModeOfWork_CreatedBy]
GO
ALTER TABLE [Ref].[Seat]  WITH CHECK ADD  CONSTRAINT [FK_Seat_Column] FOREIGN KEY([ColumnId])
REFERENCES [Ref].[Column] ([ColumnId])
GO
ALTER TABLE [Ref].[Seat] CHECK CONSTRAINT [FK_Seat_Column]
GO
ALTER TABLE [Ref].[Seat]  WITH CHECK ADD  CONSTRAINT [FK_Seat_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Ref].[Seat] CHECK CONSTRAINT [FK_Seat_CreatedBy]
GO
ALTER TABLE [Ref].[Seat]  WITH CHECK ADD  CONSTRAINT [FK_Seat_ModifiedBy] FOREIGN KEY([ModifiedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Ref].[Seat] CHECK CONSTRAINT [FK_Seat_ModifiedBy]
GO
ALTER TABLE [Ref].[WorkingDay]  WITH CHECK ADD  CONSTRAINT [FK_WorkingDay_CreatedBy] FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[Employee] ([EmployeeId])
GO
ALTER TABLE [Ref].[WorkingDay] CHECK CONSTRAINT [FK_WorkingDay_CreatedBy]
GO
USE [master]
GO
ALTER DATABASE [DeskBookDev] SET  READ_WRITE 
GO
