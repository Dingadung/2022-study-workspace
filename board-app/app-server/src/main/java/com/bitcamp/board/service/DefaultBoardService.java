package com.bitcamp.board.service;

import java.util.List;

import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;
import com.bitcamp.sql.DataSource;

// 비즈니스 로직을 수행하는 객체
// - 메서드 이름은 업무와 관련된 이름을 사용한다.
//
public class DefaultBoardService implements BoardService{
    DataSource ds;
    BoardDao boardDao;

    public DefaultBoardService(BoardDao boardDao, DataSource ds) {
        this.boardDao = boardDao;
        this.ds = ds;
    }

    @Override
    public void add(Board board) throws Exception {
        ds.getConnection().setAutoCommit(false);
        try {
            // 1) 게시글 등록
            if (boardDao.insert(board) == 0) {
                throw new Exception("게시글 등록 실패!");
            }

            // 2) 첨부파일 등록
            boardDao.insertFiles(board);
            ds.getConnection().commit();
        } catch (Exception e) {
            ds.getConnection().rollback();
            throw e;

        } finally {
            ds.getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean update(Board board) throws Exception {
        ds.getConnection().setAutoCommit(false);
        try {        
            // 1) 게시글 변경
            if (boardDao.update(board) == 0) {
                return false;
            }
            // 2) 첨부파일 추가
            boardDao.insertFiles(board);

            ds.getConnection().commit();
            return true;
        } catch (Exception e) {
            ds.getConnection().rollback();
            throw e;
        } finally {
            ds.getConnection().setAutoCommit(true);
        }
    }

    @Override
    public Board get(int no) throws Exception {
        return boardDao.findByNo(no);
    }

    @Override
    public boolean delete(int no) throws Exception {
        ds.getConnection().setAutoCommit(false);
        try {   
            // 1) 첨부파일 삭제
            boardDao.deleteFiles(no);

            // 2) 게시글 삭제
            boolean result = boardDao.delete(no) > 0;

            ds.getConnection().commit();

            return result;
        } catch (Exception e) {
            ds.getConnection().rollback();
            throw e;
        } finally {
            ds.getConnection().setAutoCommit(true);
        }
    }

    @Override
    public List<Board> list() throws Exception {
        return boardDao.findAll();
    }

    @Override
    public AttachedFile getAttachedFile(int fileNo) throws Exception {
        return boardDao.findFileByNo(fileNo);
    }

    @Override
    public boolean deleteAttachedFile(int fileNo) throws Exception {
        return boardDao.deleteFile(fileNo) > 0;
    }

}








