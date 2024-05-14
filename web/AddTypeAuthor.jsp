<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Type Author</title>
        <style>
            body {
                display: flex;
                flex-direction:column;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                padding: 0;
  background-image: linear-gradient(to bottom right, #B2DFEE, pink);
  background-repeat: no-repeat;
  align-items: center; /* Thêm thuộc tính này để căn giữa nội dung theo chiều dọc */
  min-height: 100vh; /* Đảm bảo body có chiều cao ít nhất bằng chiều cao của viewport */
            }
        </style>
    </head>
    <body>
        <div style="display: flex;">
            <div style="padding: 50px;">
        <jsp:include page="addAuthorName.jsp" />
        <jsp:include page="addTheme.jsp" />
        </div>
        <div style="padding: 50px;">
        <jsp:include page="addGenre.jsp" />
        <div>
        <jsp:include page="addFormat.jsp" />
        </div>
           </div>
           </div>
    </body>
</html>