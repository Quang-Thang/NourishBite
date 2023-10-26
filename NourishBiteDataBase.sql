-- Tạo database
CREATE DATABASE NourishBiteDatabase;

-- Sử dụng database mới tạo
USE NourishBiteDatabase;

-- Tạo bảng User
CREATE TABLE [User] (
    UserId INT IDENTITY(1,1) PRIMARY KEY,
    Email VARCHAR(255) NOT NULL,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Description TEXT,
    Role VARCHAR(50)
);

-- Tạo bảng Notification
CREATE TABLE [Notification] (
    NotificationId INT IDENTITY(1,1) PRIMARY KEY,
    Content TEXT NOT NULL,
    SentTime DATETIME NOT NULL,
    UserId INT,
    FOREIGN KEY (UserId) REFERENCES [User](UserId)
);

-- Tạo bảng Conversation
CREATE TABLE [Conversation] (
    ConversationId INT IDENTITY(1,1) PRIMARY KEY,
    SenderId INT,
    ReceiverId INT,
    StartTime DATETIME NOT NULL,
    FOREIGN KEY (SenderId) REFERENCES [User](UserId),
    FOREIGN KEY (ReceiverId) REFERENCES [User](UserId)
);

-- Tạo bảng Message
CREATE TABLE [Message] (
    MessageId INT IDENTITY(1,1) PRIMARY KEY,
    SenderId INT,
    Content TEXT NOT NULL,
    ConversationId INT,
    FOREIGN KEY (SenderId) REFERENCES [User](UserId),
    FOREIGN KEY (ConversationId) REFERENCES Conversation(ConversationId)
);

-- Tạo bảng Material
CREATE TABLE Material (
    MaterialId INT IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Description TEXT
);

-- Tạo bảng Product
CREATE TABLE Product (
    ProductId INT IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Description TEXT,
    Image VARCHAR(255),
    Material INT,
    FOREIGN KEY (Material) REFERENCES Material(MaterialId)
);



-- Tạo bảng Order
CREATE TABLE [Order] (
    OrderId INT IDENTITY(1,1) PRIMARY KEY,
    OrderDate DATETIME NOT NULL,
    Status VARCHAR(50) NOT NULL,
    Total DECIMAL(10, 2) NOT NULL,
    UserId INT,
    FOREIGN KEY (UserId) REFERENCES [User](UserId)
);

-- Tạo bảng OrderDetail
CREATE TABLE OrderDetail (
    OrderDetailId INT IDENTITY(1,1) PRIMARY KEY,
    OrderId INT,
    ProductId INT,
    Amount INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (OrderId) REFERENCES [Order](OrderId),
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId)
);

-- Tạo bảng Payment
CREATE TABLE Payment (
    PaymentId INT IDENTITY(1,1) PRIMARY KEY,
    PaymentDate DATETIME NOT NULL,
    PaymentMethod VARCHAR(50) NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    UserId INT,
    FOREIGN KEY (UserId) REFERENCES [User](UserId)
);



-- Thêm dữ liệu mẫu vào bảng User
INSERT INTO [User] (Email, Username, Password, Description, Role)
VALUES
    ('user1@example.com', 'user1', 'password1', 'User 1 Description', 'Customer'),
    ('user2@example.com', 'user2', 'password2', 'User 2 Description', 'Customer'),
    ('admin@example.com', 'admin', 'adminpassword', 'Admin User', 'Admin');

-- Thêm dữ liệu mẫu vào bảng Material
INSERT INTO Material (Name, Description)
VALUES
    ('Almond Flour', 'Blanched almond flour for keto baking'),
    ('Coconut Flour', 'Fine coconut flour for low-carb recipes'),
    ('Stevia', 'Natural sweetener for sugar-free desserts');

-- Thêm dữ liệu mẫu vào bảng Product
INSERT INTO Product (Name, Price, Description, Image, Material)
VALUES
    ('Almond Cake', 15.99, 'Delicious almond cake for keto enthusiasts', 'almond_cake.jpg', 1),
    ('Coconut Brownies', 12.99, 'Moist coconut brownies with dark chocolate', 'coconut_brownies.jpg', 2),
    ('Stevia Cookies', 8.99, 'Sugar-free cookies sweetened with stevia', 'stevia_cookies.jpg', 3);

-- Thêm dữ liệu mẫu vào bảng Order
INSERT INTO [Order] (OrderDate, Status, Total, UserId)
VALUES
    ('2023-10-20 10:00:00', 'Pending', 28.98, 1),
    ('2023-10-21 15:30:00', 'Shipped', 12.99, 2);

-- Thêm dữ liệu mẫu vào bảng OrderDetail
INSERT INTO OrderDetail (OrderId, ProductId, Amount, Price)
VALUES
    (1, 1, 1, 15.99),
    (1, 3, 1, 8.99),
    (2, 2, 1, 12.99);

-- Thêm dữ liệu mẫu vào bảng Payment
INSERT INTO Payment (PaymentDate, PaymentMethod, Amount, UserId)
VALUES
    ('2023-10-20 10:30:00', 'Credit Card', 28.98, 1),
    ('2023-10-21 16:00:00', 'PayPal', 12.99, 2);