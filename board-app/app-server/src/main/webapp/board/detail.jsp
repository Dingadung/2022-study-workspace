<%@ page language="java" 
contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>JWS</title>
    </head>
    <body>
    <h1>지민이의 게시글 상세정보!JSP+Servlet+EL</h1>
  
        <form action='update'>
        <h2>지민이게시글 상세보기>o</h2>

        <table border = '1'>

           <tr>
        <th>번호</th>
               <td><input name ='no' type='number' value='${board.no}'readonly></td>
           </tr>

           <tr>
        <th>제목</th>
               <td><input name='title' type='text' value='${board.title}'size='60'></td>
           </tr>

           <tr>
        <th>내용</th>
              <td><textarea name='content' rows='10' cols='60'>${board.content}</textarea></td>
           </tr>

           <tr>
        <th>조회수</th>
               <td>${board.viewCount}</td>
           </tr>

           <tr>
        <th>작성자</th>
               <td>${board.memberNo}</td>
           </tr>

           <tr>
        <th>등록일</th>
               <td>${board.createdDate}</td> 
           </tr>

        </table>
        <p>
        <button type='submit'>변경</button>
        <a href='delete?no=${board.no}'>삭제</a>
        </p>
        </form>
    </body>
    </html>
