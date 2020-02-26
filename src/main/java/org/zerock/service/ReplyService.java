package org.zerock.service;

import java.util.List;

import org.zerock.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> listReply(Integer bno) throws Exception;
	public void createReply(ReplyVO vo) throws Exception;
	public void updateReply(ReplyVO vo) throws Exception;
	public void deleteReply(Integer rno) throws Exception;
}
