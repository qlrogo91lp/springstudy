package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
import org.zerock.service.BoardService;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/")
	public String home() throws Exception {
		logger.info("Home >>> : ");
		
		return "redirect:/board/listAll";
	}
	
	// ��� �Է���
	@RequestMapping(value="/board/register", method=RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("register get >>> : ");
	}
	// ��� �Ϸ�
	@RequestMapping(value="/board/register", method=RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register post >>> : ");
		logger.info("BoardController/registerPOST >>> : " + board.toString());

		service.regist(board);
		rttr.addFlashAttribute("msg", "���"); // ���������� ������ ������ URI���� ������ ����
		//model.addAttribute("result", "success");
		
		// return "/board/success";
		return "redirect:/board/listAll";
	}
	// ������
	public void listAll(Model model, Criteria cri) throws Exception {
		// logger.info("show all list >>> : ");
		logger.info("show all list with Criteria >>> : ");
		
		// model.addAttribute("list", service.listAll());
		model.addAttribute("list", service.listCriteria(cri));
	}
	// ��ȸ
	@RequestMapping(value="/board/read", method=RequestMethod.GET)
	public ModelAndView read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bno", service.read(bno));
		mav.addObject("cri", cri);
		mav.setViewName("/board/read");
		
		return mav;
	}
	// ����
	@RequestMapping(value="/board/remove", method=RequestMethod.GET)
	public String remove(@RequestParam("bno") int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "����");
		
		return "redirect:/board/listAll";
	}
	// ���� �Է���
	@RequestMapping(value="/board/modify", method=RequestMethod.GET)
	public ModelAndView modifyGET(int bno, SearchCriteria cri) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		logger.info("BoardController.modifyGET/cri.getPage() >>> : " + cri.getPage());
		logger.info("BoardController.modifyGET/cri.getPerPageNum() >>> : " + cri.getPerPageNum());
		
		mav.addObject("bno", service.read(bno));
		mav.addObject("cri", cri);
		mav.setViewName("/board/modify");
		
		return mav;
	}
	// ���� �Ϸ�
	@RequestMapping(value="/board/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("mod post >>> : ");
		
		service.modify(board);
		
		logger.info("BoardController.modifyPOST/cri.getPage() >>> : " + cri.getPage());
		logger.info("BoardController.modifyPOST/cri.getPerPageNum() >>> : " + cri.getPerPageNum());
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "����");
		
		return "redirect:/board/listAll";
	}
	// ��� �˻� ��� �߰�
	// �信�� ������Ʈ�� ������ page���� �Ѿ��
	@RequestMapping(value = "/board/listAll", method = RequestMethod.GET)
	public ModelAndView listPage(@ModelAttribute("cri") SearchCriteria cri) throws Exception {
		logger.info("BoardController/listPage.cri.toString() >>> : " + cri.toString());
		logger.info("BoardController.listPage/cri.getPage() >>> : " + cri.getPage());
		logger.info("BoardController.listPage/cri.getPerPageNum() >>> : " + cri.getPerPageNum());
		
		ModelAndView mav = new ModelAndView();
		// model.addAttribute("list", service.listCriteria(cri));
		mav.addObject("list", service.listSearch(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		// pageMaker.setTotalCount(service.countPaging(cri));
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		mav.addObject("pageMaker", pageMaker);
		
		logger.info("BoardController.listPage/pageMaker.getStartpage() >>> : " + pageMaker.getStartPage());
		logger.info("BoardController.listPage/pageMaker.getEndPage() >>> : " + pageMaker.getEndPage());
		
		mav.setViewName("/board/listAll");
		
		return mav;
	}
/*
	@RequestMapping(value="/board/listAll", method=RequestMethod.GET)
	public ModelAndView listPage(Criteria cri) throws Exception {
		logger.info("BoardController.listPage/cri.getPage() >>> : " + cri.getPage());
		logger.info("BoardController.listPage/cri.getPerPageNum() >>> : " + cri.getPerPageNum());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", service.listCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.countPaging(cri));
		
		mav.addObject("pageMaker", pageMaker);
		
		logger.info("BoardController.listPage/pageMaker.getStartpage() >>> : " + pageMaker.getStartPage());
		logger.info("BoardController.listPage/pageMaker.getEndPage() >>> : " + pageMaker.getEndPage());
		
		mav.setViewName("/board/listAll");
		
		return mav;
	}
*/
}
