
--Kavan seat 2
SELECT * FROM Employee WHERE EmployeeId = 'db001219-0d36-4804-ba00-459b58347beb';
--Abhishek seat 1
SELECT * FROM SeatConfiguration WHERE EmployeeId = 'db001219-0d36-4804-ba00-459b58347beb';
--Abhishek seat 1
SELECT * FROM SeatConfiguration WHERE EmployeeId = 'db001219-0d36-4804-ba00-459b58347beb';
SELECT * FROM SeatConfiguration WHERE EmployeeId = 'db001219-0d36-4804-ba00-459b58347beb';

SELECT * FROM Employee WHERE EmailId ='raj@gmail.com';

--SELECT s FROM SeatRequest s WHERE s.requestStatus=1 ORDER BY s.bookingDate AND s.createdDate DESC"
SELECT TOP 1 * FROM Booking.SeatRequest s  WHERE s.RequestStatus=2  ORDER BY  s.BookingDate , s.CreatedDate DESC  ;

SELECT * FROM Booking.SeatRequest;
SELECT * FROM Ref.Seat;
SELECT * FROM Booking.SeatRequest s WHERE s.EmployeeId='db001219-0d36-4804-ba00-459b58347beb' AND  s.SeatId= 1 AND s.BookingDate='2023-06-01' AND DeletedDate=NULL;
SELECT * FROM Ref.Seat g WHERE SeatId=1;
TRUNCATE TABLE [Booking].[SeatRequest];
TRUNCATE TABLE [dbo].[SeatConfiguration]
  TRUNCATE TABLE [dbo].[EmployeeWorkingDays]

  SELECT * FROM Booking.SeatRequest WHERE SeatId = 1

  Select Count(*) FROM Booking.SeatRequest WHERE EmployeeId='8a71184d-39cf-4815-84d7-d9c79c161abb' AND BookingDate= '2023-06-08' AND DeletedDate = null;

  UPDATE  Booking.SeatRequest  SET SeatRequest.RequestStatus = 1 WHERE EmployeeId = 'db001219-0d36-4804-ba00-459b58347beb';
  DELETE  FROM Booking.SeatRequest WHERE SeatRequestId in(7,8);;
create schema Booking
GO

use DeskBookDev;

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

ALTER TABLE [Booking].[SeatRequest] ADD  CONSTRAINT [DF_SeatRequest_CreatedDate]  DEFAULT (getdate()) FOR [CreatedDate]
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