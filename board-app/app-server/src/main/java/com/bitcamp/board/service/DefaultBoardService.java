package com.bitcamp.board.service;

import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.AttachedFile;
import com.bitcamp.board.domain.Board;

public class DefaultBoardService implements BoardService {
  PlatformTransactionManager txManager; 
  BoardDao boardDao;

  public DefaultBoardService(BoardDao boardDao,PlatformTransactionManager txManager) {
    this.boardDao = boardDao;
    this.txManager = txManager;
  }

  @Override
  public void add(Board board) throws Exception {
    // 스프링에서 제공하는 트랜잭션을 사용할 때는
    // 트랜잭션 실행  정책을 정의 해야한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    TransactionStatus status = txManager.getTransaction(def);
    try {
      // 1) 게시글 등록
      if (boardDao.insert(board) == 0) {
        throw new Exception("게시글 등록 실패!");
      }

      // 2) 첨부파일 등록
      boardDao.insertFiles(board);
      txManager.commit(status);

    } catch (Exception e) {
      txManager.rollback(status);
      throw e;

    } 
  }

  @Override
  public boolean update(Board board) throws Exception {
    // 스프링에서 제공하는 트랜잭션을 사용할 때는
    // 트랜잭션 실행  정책을 정의 해야한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    TransactionStatus status = txManager.getTransaction(def);
    try {
      // 1) 게시글 변경
      if (boardDao.update(board) == 0) {
        return false;
      }
      // 2) 첨부파일 추가
      boardDao.insertFiles(board);

      txManager.commit(status);
      return true;
    } catch (Exception e) {
      txManager.rollback(status);
      throw e;
    } 
  }

  @Override
  public Board get(int no) throws Exception {
    return boardDao.findByNo(no);
  }

  @Override
  public boolean delete(int no) throws Exception {
    // 스프링에서 제공하는 트랜잭션을 사용할 때는
    // 트랜잭션 실행  정책을 정의 해야한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    TransactionStatus status = txManager.getTransaction(def);
    try {
      // 1) 첨부파일 삭제
      boardDao.deleteFiles(no);

      // 2) 게시글 삭제
      boolean result = boardDao.delete(no) > 0;

      txManager.commit(status);

      return result;
    } catch (Exception e) {
      txManager.rollback(status);
      throw e;
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