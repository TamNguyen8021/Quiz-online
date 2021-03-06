USE [master]
GO
/****** Object:  Database [J3.L.P0007]    Script Date: 6/3/2020 8:20:29 PM ******/
CREATE DATABASE [J3.L.P0007]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'J3.L.P0007', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\J3.L.P0007.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'J3.L.P0007_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\J3.L.P0007_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [J3.L.P0007] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [J3.L.P0007].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [J3.L.P0007] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [J3.L.P0007] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [J3.L.P0007] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [J3.L.P0007] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [J3.L.P0007] SET ARITHABORT OFF 
GO
ALTER DATABASE [J3.L.P0007] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [J3.L.P0007] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [J3.L.P0007] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [J3.L.P0007] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [J3.L.P0007] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [J3.L.P0007] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [J3.L.P0007] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [J3.L.P0007] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [J3.L.P0007] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [J3.L.P0007] SET  DISABLE_BROKER 
GO
ALTER DATABASE [J3.L.P0007] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [J3.L.P0007] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [J3.L.P0007] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [J3.L.P0007] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [J3.L.P0007] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [J3.L.P0007] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [J3.L.P0007] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [J3.L.P0007] SET RECOVERY FULL 
GO
ALTER DATABASE [J3.L.P0007] SET  MULTI_USER 
GO
ALTER DATABASE [J3.L.P0007] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [J3.L.P0007] SET DB_CHAINING OFF 
GO
ALTER DATABASE [J3.L.P0007] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [J3.L.P0007] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [J3.L.P0007] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'J3.L.P0007', N'ON'
GO
ALTER DATABASE [J3.L.P0007] SET QUERY_STORE = OFF
GO
USE [J3.L.P0007]
GO
/****** Object:  Table [dbo].[tbl_accounts]    Script Date: 6/3/2020 8:20:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_accounts](
	[Email] [varchar](50) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Password] [varchar](max) NOT NULL,
	[Role] [varchar](20) NOT NULL,
	[Status] [varchar](20) NOT NULL,
 CONSTRAINT [PK_tbl_accounts] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_answers]    Script Date: 6/3/2020 8:20:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_answers](
	[QuestionID] [int] NOT NULL,
	[AnswerID] [int] IDENTITY(0,1) NOT NULL,
	[Answer_content] [varchar](max) NOT NULL,
	[Answer_correct] [bit] NOT NULL,
	[Status] [varchar](20) NOT NULL,
 CONSTRAINT [PK_tbl_answers] PRIMARY KEY CLUSTERED 
(
	[AnswerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_history]    Script Date: 6/3/2020 8:20:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_history](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[SubjectID] [varchar](10) NOT NULL,
	[Correct_answer] [int] NOT NULL,
	[Total_question] [int] NOT NULL,
	[Score] [float] NOT NULL,
	[DoTime] [datetime] NOT NULL,
 CONSTRAINT [PK_tbl_history_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_questions]    Script Date: 6/3/2020 8:20:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_questions](
	[QuestionID] [int] IDENTITY(0,1) NOT NULL,
	[Question_content] [varchar](max) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[SubjectID] [varchar](10) NOT NULL,
	[Status] [varchar](20) NOT NULL,
 CONSTRAINT [PK_tbl_Questions] PRIMARY KEY CLUSTERED 
(
	[QuestionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_roles]    Script Date: 6/3/2020 8:20:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_roles](
	[Role] [varchar](20) NOT NULL,
 CONSTRAINT [PK_tbl_roles] PRIMARY KEY CLUSTERED 
(
	[Role] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_status]    Script Date: 6/3/2020 8:20:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_status](
	[Status] [varchar](20) NOT NULL,
 CONSTRAINT [PK_tbl_status] PRIMARY KEY CLUSTERED 
(
	[Status] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_subjects]    Script Date: 6/3/2020 8:20:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_subjects](
	[SubjectID] [varchar](10) NOT NULL,
	[SubjectName] [varchar](50) NULL,
	[Question_number] [int] NOT NULL,
	[Time] [int] NOT NULL,
 CONSTRAINT [PK_tbl_Subjects] PRIMARY KEY CLUSTERED 
(
	[SubjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tbl_accounts] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'11@com.com', N'123', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Student', N'New')
INSERT [dbo].[tbl_accounts] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'coba@gmail.com', N'Hai', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Student', N'New')
INSERT [dbo].[tbl_accounts] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'ha@gmail.com', N'Vuong', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Admin', N'Active')
INSERT [dbo].[tbl_accounts] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'langtuy2@gmail.com', N'Thanh Y', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Student', N'Active')
GO
SET IDENTITY_INSERT [dbo].[tbl_answers] ON 

INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (1, 0, N'A status of 200 to 299 signifies that the request was successful.', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (1, 1, N'A status of 300 to 399 is informational messages.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (1, 2, N'A status of 400 to 499 indicates an error in the server.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (1, 3, N'A status of 500 to 599 indicates an error in the client.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (3, 4, N'Servlet', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (3, 5, N'Http', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (3, 6, N'HttpServletRequest', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (3, 7, N'HttpServletResponse', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (4, 8, N'response.setContentType("image/gif");', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (4, 9, N'response.setType("application/gif");', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (4, 10, N'response.setContentType("application/bin");', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (4, 11, N'response.setType("image/gif");', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (6, 12, N'Information resources(sources) on the Internet', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (6, 13, N'E-mail addresses for use in the Internet', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (6, 14, N'IP addresses of servers connected to the Internet', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (6, 15, N'Owners of PCs connected to the Internet', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (7, 16, N'It is an XML document.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (7, 17, N'It cannot be unpackaged by the container.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (7, 18, N'It is used by web application developer to deliver the web application in a single unit.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (7, 19, N'It contains web components such as servlets as well as EJBs.', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (8, 20, N'Web Container', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (8, 21, N'EJB Container', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (8, 22, N'Servlets', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (8, 23, N'Applets', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (9, 24, N'Cookie', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (9, 25, N'Session', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (9, 26, N'Request', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (9, 27, N'Response', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (10, 28, N'The container is shutdown and brought up again.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (10, 29, N'No request comes from the client for more than "session timeout" period.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (10, 30, N'A servlet explicitly calls invalidate() on a session object.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (10, 31, N'If the session time out is set to 0 using setMaxInactiveInterval() method.', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (11, 32, N'setMaxInactiveInterval(-1)', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (11, 33, N'setTimeOut(Integer.MAX_INT)', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (11, 34, N'setMaxInactiveInterval(Integer.MAX_INT)', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (11, 35, N'setTimeOut(-1)', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (12, 36, N'session, page, application, request', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (12, 37, N'session, page, application, global', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (12, 39, N'session, page, application, context', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (12, 40, N'session, page, response, request', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (13, 41, N'page', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (13, 42, N'session', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (13, 43, N'application', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (13, 44, N'request', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (14, 45, N'<%@page import=''java.util.*'' %>', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (14, 47, N'<%@import package=''java.util.*'' %>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (14, 48, N'<%@ package import =''java.util.*'' %>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (14, 49, N'<%! page import=''java.util.*'' %>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (15, 50, N'HttpServletRequest and ServletContext respectively.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (15, 51, N'ServletRequest and ServletConfig respectively.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (15, 52, N'ServletRequest and PageContext respectively.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (15, 53, N'HttpServletRequest and PageContext respectively', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (16, 54, N'<jsp:expression=x/>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (16, 55, N'<jsp:expression>x</jsp:expression>', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (16, 56, N'<jsp:statement>x</jsp:statement>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (16, 57, N'<jsp:declaration>x</jsp:declaration>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (18, 58, N'<%= request.getParameter("myParm") %>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (18, 59, N'<% String s = getInitParameter("myParm"); %>', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (18, 60, N'<% = application.getInitParameter("myParm") %>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (18, 61, N'<%= getParameter("myParm") %>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (19, 62, N'jsp:useBean.property', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (19, 63, N'jsp:useBean.setProperty', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (19, 64, N'jsp:property', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (19, 65, N'jsp:setProperty', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (20, 66, N'The ID of the JavaBean for which a property (or properties) will be set.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (20, 67, N'The name of the property to set. Specifying "*" for this attribute causes the JSP to match the request parameters to the properties of the bean.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (20, 68, N'If request parameter names do not match bean property names, this attribute can be used to specify which request parameter should be used to obtain the value for a specific bean property.', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (20, 69, N'The value to assign to a bean property. The value typically is the result of a JSP expression.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (21, 70, N'The ID of the JavaBean for which a property (or properties) will be set.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (21, 71, N'The name of the property to set. Specifying "*" for this attribute causes the JSP to match the request parameters to the properties of the bean.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (21, 72, N'If request parameter names do not match bean property names, this attribute can be used to specify which request parameter should be used to obtain the value for a specific bean property.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (21, 73, N'The value to assign to a bean property. The value typically is the result of a JSP expression.', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (22, 74, N'The name used to manipulate the Java object with actions <jsp:setProperty> and <jsp:getProperty>. A variable of this name is also declared for use in JSP scripting elements. The name specified here is case sensitive.', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (22, 75, N'The scope in which the Java object is accessible—page, request, session or application. The default scope is page.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (22, 76, N'The fully qualified class name of the Java object.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (22, 77, N'The name of a bean that can be used with method instantiate of class java.beans.Beans to load a JavaBean into memory.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (23, 78, N'Any exceptions in the current page that are not caught are sent to the error page for processing. The error page implicit object exception references the original exception.', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (23, 79, N'Specifies if the current page is an error page that will be invoked in response to an error on another page. If the attribute value is true, the implicit object exception is created and references the original exception that occurred.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (23, 80, N'Specifies the MIME type of the data in the response to the client. The default type is text/html.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (23, 81, N'Specifies the class from which the translated JSP will be inherited. This attribute must be a fully qualified package and class name.', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (24, 82, N'<jsp:setProperty>', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (24, 83, N'<jsp:getProperty>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (24, 84, N'<jsp:useBean>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (24, 85, N'<jsp:include>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (25, 86, N'<jsp:useBean id="beanName1" class="a.b.MyBean" type="a.b.MyInterface" />', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (25, 87, N'<% String className = "a.b.MyBean"; %>
<jsp:useBean id="beanName2" class="<%=className%>" />', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (25, 88, N'<% String beanName = "beanName3"; %>
<jsp:useBean id="<%=beanName3%>" class="a.b.MyBean" />', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (25, 89, N'<% String propName = "soleProp"; %>
<jsp:getProperty name="beanName1" property="<%=propName%>" />
/>', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (28, 98, N'RecordSet', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (28, 99, N'ResultSet', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (28, 100, N'DataSet', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (28, 101, N'RowSet', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (29, 102, N'Single -tier and two tier', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (29, 103, N'None of the others', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (29, 104, N'Three-tier and four tier', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (29, 105, N'Two-tier and three-tier', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (30, 106, N'Thread inherit their priority from their parent thread', 0, N'Deactive')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (30, 107, N'Thread are guanteed to run with the priority that you set using the set Priority() method', 1, N'Deactive')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (30, 108, N'Every thread starts executing with a priority of 5', 0, N'Deactive')
GO
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (30, 109, N'Thread priority is an integer ranging from 1 to 100', 0, N'Deactive')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (31, 110, N'java.awt.FontMetric', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (31, 111, N'java.util.FontMetric', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (31, 112, N'javax.swing.FontMetric', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (31, 113, N'java.awt.FontMetrics', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (32, 114, N'Vary the priorities of your threads', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (32, 115, N'Synchronize access to all shared variables', 0, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (32, 116, N'There is no single technique that can guarantee non-deadlocking code', 1, N'Active')
INSERT [dbo].[tbl_answers] ([QuestionID], [AnswerID], [Answer_content], [Answer_correct], [Status]) VALUES (32, 117, N'Make sure all threads yield from time to time', 0, N'Active')
SET IDENTITY_INSERT [dbo].[tbl_answers] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_history] ON 

INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (1, N'langtuy2@gmail.com', N'PRJ321', 0, 5, 0, CAST(N'2020-05-28T08:09:14.587' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (2, N'langtuy2@gmail.com', N'PRJ321', 3, 5, 6, CAST(N'2020-05-28T08:15:28.257' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (3, N'langtuy2@gmail.com', N'PRJ321', 1, 5, 2, CAST(N'2020-05-28T08:52:05.310' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (12, N'langtuy2@gmail.com', N'PRJ321', 3, 5, 6, CAST(N'2020-05-28T11:42:33.150' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (13, N'langtuy2@gmail.com', N'PRJ321', 5, 5, 10, CAST(N'2020-05-28T11:49:12.520' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (19, N'langtuy2@gmail.com', N'PRJ321', 1, 5, 2, CAST(N'2020-05-29T06:49:55.827' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (20, N'langtuy2@gmail.com', N'PRJ321', 0, 5, 0, CAST(N'2020-05-29T07:03:29.973' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (21, N'langtuy2@gmail.com', N'PRJ321', 0, 5, 0, CAST(N'2020-05-29T07:13:05.033' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (22, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-05-30T13:35:56.360' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (23, N'langtuy2@gmail.com', N'PRJ311', 3, 5, 6, CAST(N'2020-05-30T20:30:10.250' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (24, N'langtuy2@gmail.com', N'PRJ311', 1, 5, 2, CAST(N'2020-05-31T09:37:04.657' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (201, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-05-31T09:40:44.267' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (202, N'langtuy2@gmail.com', N'PRJ311', 1, 5, 2, CAST(N'2020-05-31T09:46:03.780' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (203, N'langtuy2@gmail.com', N'PRJ311', 4, 5, 8, CAST(N'2020-05-31T09:54:24.487' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (204, N'langtuy2@gmail.com', N'PRJ311', 3, 5, 6, CAST(N'2020-05-31T09:55:08.767' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (205, N'langtuy2@gmail.com', N'PRJ311', 3, 5, 6, CAST(N'2020-05-31T09:56:10.807' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (206, N'langtuy2@gmail.com', N'PRJ311', 4, 5, 8, CAST(N'2020-05-31T10:02:24.880' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (207, N'langtuy2@gmail.com', N'PRJ321', 2, 5, 4, CAST(N'2020-05-31T10:05:11.317' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (208, N'langtuy2@gmail.com', N'PRJ321', 3, 5, 6, CAST(N'2020-05-31T10:06:29.440' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (209, N'langtuy2@gmail.com', N'PRJ311', 4, 5, 8, CAST(N'2020-05-31T10:07:03.113' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (213, N'langtuy2@gmail.com', N'PRJ311', 2, 5, 4, CAST(N'2020-06-02T16:05:32.713' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (214, N'langtuy2@gmail.com', N'PRJ311', 4, 5, 8, CAST(N'2020-06-02T16:38:17.623' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (215, N'langtuy2@gmail.com', N'PRJ321', 3, 5, 6, CAST(N'2020-06-02T16:39:42.907' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (216, N'langtuy2@gmail.com', N'PRJ321', 4, 5, 8, CAST(N'2020-06-02T16:41:10.743' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (217, N'langtuy2@gmail.com', N'PRJ321', 1, 5, 2, CAST(N'2020-06-02T16:44:33.397' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (218, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-06-03T14:43:28.967' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (219, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-06-03T14:46:42.453' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (220, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-06-03T14:47:31.027' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (221, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-06-03T14:48:31.170' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (222, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-06-03T14:48:51.827' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (223, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-06-03T14:49:12.707' AS DateTime))
INSERT [dbo].[tbl_history] ([ID], [Email], [SubjectID], [Correct_answer], [Total_question], [Score], [DoTime]) VALUES (224, N'langtuy2@gmail.com', N'PRJ311', 0, 5, 0, CAST(N'2020-06-03T16:59:23.330' AS DateTime))
SET IDENTITY_INSERT [dbo].[tbl_history] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_questions] ON 

INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (1, N'Which of the following statements are correct about the status of the Http response? Select the one correct answer', CAST(N'2020-01-01T07:20:06.670' AS DateTime), N'PRJ311', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (3, N'Classes HttpServlet and GenericServlet implement the ___ interface.', CAST(N'2020-01-01T00:00:06.673' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (4, N'You have to send a gif image to the client as a response to a request. Which of the following calls will you have to make?', CAST(N'2020-05-18T07:00:06.677' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (6, N'Which of the following is indicated by URL, which is used on the Internet?', CAST(N'2020-05-01T08:00:06.680' AS DateTime), N'PRJ311', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (7, N'Identify correct statement about a WAR file.(Choose one)', CAST(N'2020-01-02T09:00:06.683' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (8, N'A .................... manages the threading for the servlets and JSPs and provides the necessary interface with the Web server.', CAST(N'2020-01-01T09:30:06.687' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (9, N'A ________has a name, a single value, and optional attributes such as a comment, path and domain qualifiers, a maximum age, and a version number.', CAST(N'2020-01-01T08:20:06.690' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (10, N'Which is NOT a standard technique for a session be definitely invalidated?', CAST(N'2020-01-01T10:00:06.693' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (11, N'Which method can be invoked on a session object so that it is never invalidated by the servlet container automatically?', CAST(N'2020-01-01T10:30:06.697' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (12, N'What are the different scope values for the ‹jsp:useBean›? (Choose one correct answer)', CAST(N'2020-01-01T10:20:06.700' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (13, N'Name the default value of the scope attribute of <jsp:useBean>', CAST(N'2020-01-18T06:00:06.703' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (14, N'Select the correct directive statement insert into the first line of following lines of code (1 insert code here):

Today''s Date is <%=new Date()%>', CAST(N'2020-01-18T06:20:06.707' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (15, N'For JSP scopes of request and page, what type of object is used to store the attributes?', CAST(N'2020-01-11T06:30:06.710' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (16, N'Which of the following correctly represents the following JSP statement? Select one correct answer.
<%=x%>', CAST(N'2020-01-21T08:15:06.713' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (18, N'Which technique is likely to return an initialization parameter for a JSP page?', CAST(N'2020-01-01T11:00:06.717' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (19, N'Which jsp tag can be used to set bean property?', CAST(N'2020-01-21T11:10:06.720' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (20, N'Which statements are BEST describe param attribute of <jsp:setProperty param=.... /> Action?', CAST(N'2020-01-21T11:20:06.723' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (21, N'Which statements are BEST describe value attribute of <jsp:setProperty value=... /> action?', CAST(N'2020-01-21T11:30:06.727' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (22, N'Which statements are BEST describe id attribute of <jsp:useBean id=..... /> Action?', CAST(N'2020-01-21T11:40:06.730' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (23, N'Which statements are BEST describe errorPage attribute of <%@ page errorPage=....%> directive?', CAST(N'2020-01-21T11:50:06.733' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (24, N'Action _______has the ability to match request parameters to properties of the same name in a bean by specifying "*" for attribute property.', CAST(N'2020-01-21T12:00:06.737' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (25, N'Which of the following are potentially legal lines of JSP source?', CAST(N'2020-01-21T12:10:06.740' AS DateTime), N'PRJ321', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (28, N'Statement object return SQL query result as ____ objects', CAST(N'2020-01-21T12:20:06.743' AS DateTime), N'PRJ311', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (29, N'JDBC supports ____ and____ models', CAST(N'2020-01-21T12:30:06.747' AS DateTime), N'PRJ311', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (30, N'Which of the following statements about threads is true?', CAST(N'2020-05-25T20:57:00.000' AS DateTime), N'PRJ311', N'Deactive')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (31, N'To get metric data of a font, the class ___ should be used', CAST(N'2020-05-30T20:14:13.247' AS DateTime), N'PRJ311', N'Active')
INSERT [dbo].[tbl_questions] ([QuestionID], [Question_content], [CreateDate], [SubjectID], [Status]) VALUES (32, N'How can you ensure that multithreaded code does not deadlock', CAST(N'2020-05-31T13:18:54.777' AS DateTime), N'PRJ311', N'Active')
SET IDENTITY_INSERT [dbo].[tbl_questions] OFF
GO
INSERT [dbo].[tbl_roles] ([Role]) VALUES (N'Admin')
INSERT [dbo].[tbl_roles] ([Role]) VALUES (N'Student')
GO
INSERT [dbo].[tbl_status] ([Status]) VALUES (N'Active')
INSERT [dbo].[tbl_status] ([Status]) VALUES (N'Deactive')
INSERT [dbo].[tbl_status] ([Status]) VALUES (N'New')
GO
INSERT [dbo].[tbl_subjects] ([SubjectID], [SubjectName], [Question_number], [Time]) VALUES (N'PRJ311', N'Java Desktop', 5, 7)
INSERT [dbo].[tbl_subjects] ([SubjectID], [SubjectName], [Question_number], [Time]) VALUES (N'PRJ321', N'Java Web Development', 5, 7)
GO
ALTER TABLE [dbo].[tbl_accounts]  WITH CHECK ADD  CONSTRAINT [FK_tbl_accounts_tbl_roles] FOREIGN KEY([Role])
REFERENCES [dbo].[tbl_roles] ([Role])
GO
ALTER TABLE [dbo].[tbl_accounts] CHECK CONSTRAINT [FK_tbl_accounts_tbl_roles]
GO
ALTER TABLE [dbo].[tbl_accounts]  WITH CHECK ADD  CONSTRAINT [FK_tbl_accounts_tbl_status] FOREIGN KEY([Status])
REFERENCES [dbo].[tbl_status] ([Status])
GO
ALTER TABLE [dbo].[tbl_accounts] CHECK CONSTRAINT [FK_tbl_accounts_tbl_status]
GO
ALTER TABLE [dbo].[tbl_answers]  WITH CHECK ADD  CONSTRAINT [FK_tbl_answers_tbl_questions] FOREIGN KEY([QuestionID])
REFERENCES [dbo].[tbl_questions] ([QuestionID])
GO
ALTER TABLE [dbo].[tbl_answers] CHECK CONSTRAINT [FK_tbl_answers_tbl_questions]
GO
ALTER TABLE [dbo].[tbl_answers]  WITH CHECK ADD  CONSTRAINT [FK_tbl_answers_tbl_status] FOREIGN KEY([Status])
REFERENCES [dbo].[tbl_status] ([Status])
GO
ALTER TABLE [dbo].[tbl_answers] CHECK CONSTRAINT [FK_tbl_answers_tbl_status]
GO
ALTER TABLE [dbo].[tbl_history]  WITH CHECK ADD  CONSTRAINT [FK_tbl_history_tbl_accounts] FOREIGN KEY([Email])
REFERENCES [dbo].[tbl_accounts] ([Email])
GO
ALTER TABLE [dbo].[tbl_history] CHECK CONSTRAINT [FK_tbl_history_tbl_accounts]
GO
ALTER TABLE [dbo].[tbl_history]  WITH CHECK ADD  CONSTRAINT [FK_tbl_history_tbl_subjects] FOREIGN KEY([SubjectID])
REFERENCES [dbo].[tbl_subjects] ([SubjectID])
GO
ALTER TABLE [dbo].[tbl_history] CHECK CONSTRAINT [FK_tbl_history_tbl_subjects]
GO
ALTER TABLE [dbo].[tbl_questions]  WITH CHECK ADD  CONSTRAINT [FK_tbl_questions_tbl_status] FOREIGN KEY([Status])
REFERENCES [dbo].[tbl_status] ([Status])
GO
ALTER TABLE [dbo].[tbl_questions] CHECK CONSTRAINT [FK_tbl_questions_tbl_status]
GO
ALTER TABLE [dbo].[tbl_questions]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Questions_tbl_Subjects] FOREIGN KEY([SubjectID])
REFERENCES [dbo].[tbl_subjects] ([SubjectID])
GO
ALTER TABLE [dbo].[tbl_questions] CHECK CONSTRAINT [FK_tbl_Questions_tbl_Subjects]
GO
USE [master]
GO
ALTER DATABASE [J3.L.P0007] SET  READ_WRITE 
GO
