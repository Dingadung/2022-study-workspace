package com.bitcamp.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;
import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.BoardService;

// CRUD 요청을 처리하는 페이지 컨트롤러들을 한 개의 클래스로 합친다.
@Controller // 페이지 컨트롤러에 붙이는 애노테이션
@RequestMapping("/board/")
public class BoardController {

    ServletContext sc;
    BoardService boardService;

    public BoardController(BoardService boardService, ServletContext sc) {
        this.boardService = boardService;
        this.sc = sc;
    }

    @GetMapping("form") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String form() throws Exception {
        return "/board/form.jsp";
    } // form

    @PostMapping("add") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String add(
            Board board,
            MultipartFile[] files,
            HttpSession session) throws Exception {
        board.setAttachedFiles(saveAttachedFiles(files));
        board.setWriter((Member)session.getAttribute("loginMember"));

        boardService.add(board);
        return "redirect:list";
    } // add()

    private List<AttachedFile> saveAttachedFiles(Part[] files) throws IOException, ServletException {
        List<AttachedFile> attachedFiles = new ArrayList<>();
        String dirPath = sc.getRealPath("/board/files");

        for (Part part : files) {
            if ( part.getSize() == 0) {
                continue;
            }

            String filename = UUID.randomUUID().toString();
            part.write(dirPath + "/" + filename);
            attachedFiles.add(new AttachedFile(filename));
        }
        return attachedFiles;
    }

    private List<AttachedFile> saveAttachedFiles(MultipartFile[] files) throws IOException, ServletException {
        List<AttachedFile> attachedFiles = new ArrayList<>();
        String dirPath = sc.getRealPath("/board/files");

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            String filename = UUID.randomUUID().toString();
            file.transferTo(new File(dirPath + "/" + filename));
            attachedFiles.add(new AttachedFile(filename));
        }
        return attachedFiles;
    }

    @GetMapping("list") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String list(HttpServletRequest req) throws Exception {
        req.setAttribute("boards", boardService.list());
        return "/board/list.jsp";
    } //list()

    @GetMapping("detail") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String detail(
            int no , 
            HttpServletRequest request) throws Exception {
        int boardNo = Integer.parseInt(request.getParameter("no"));

        Board board = boardService.get(boardNo);
        if (board == null) {
            throw new Exception("해당 번호의 게시글이 없습니다!");
        }

        request.setAttribute("board", board);

        return "/board/detail.jsp";
    } // detail()

    @PostMapping("update") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String update(
            Board board,
            Part[] files,
            HttpSession session)
                    throws Exception {
        board.setAttachedFiles(saveAttachedFiles(files));

        checkOwner(board.getNo(), session);

        if (!boardService.update(board)) {
            throw new Exception("게시글을 변경할 수 없습니다!");
        }

        return "redirect:list";
    }

    private void checkOwner(int boardNo, HttpSession session) throws Exception {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (boardService.get(boardNo).getWriter().getNo() != loginMember.getNo()) {
            throw new Exception("게시글 작성자가 아닙니다.");
        }
    }

    @GetMapping("delete") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String delete(
            int no,
            HttpSession session) 
                    throws Exception {

        checkOwner(no, session);
        if (!boardService.delete(no)) {
            throw new Exception("게시글을 삭제할 수 없습니다.");
        }

        return "redirect:list";
    } // delete()

    @GetMapping("fileDelete") // 요청이 들어 왔을 때 호출될 메서드에 붙이는 애노테이션
    public String fileDelete(
            int no,
            HttpSession session
            ) throws Exception {
        AttachedFile attachedFile = boardService.getAttachedFile(no); 

        Member loginMember = (Member) session.getAttribute("loginMember");
        Board board = boardService.get(attachedFile.getBoardNo()); 

        if (board.getWriter().getNo() != loginMember.getNo()) {
            throw new Exception("게시글 작성자가 아닙니다.");
        }

        if (!boardService.deleteAttachedFile(no)) {
            throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
        }

        return "redirect:detail?no=" + board.getNo();
    } // fileDelete()

}






