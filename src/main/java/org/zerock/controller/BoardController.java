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
	
	// 등록 입력폼
	@RequestMapping(value="/board/register", method=RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("register get >>> : ");
	}
	// 등록 완료
	@RequestMapping(value="/board/register", method=RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register post >>> : ");
		logger.info("BoardController/registerPOST >>> : " + board.toString());

		service.regist(board);
		rttr.addFlashAttribute("msg", "등록"); // 브라우저까지 전송은 되지만 URI에는 보이지 않음
		//model.addAttribute("result", "success");
		
		// return "/board/success";
		return "redirect:/board/listAll";
	}
	// 사용안함
	public void listAll(Model model, Criteria cri) throws Exception {
		// logger.info("show all list >>> : ");
		logger.info("show all list with Criteria >>> : ");
		
		// model.addAttribute("list", service.listAll());
		model.addAttribute("list", service.listCriteria(cri));
	}
	// 조회
	@RequestMapping(value="/board/read", method=RequestMethod.GET)
	public ModelAndView read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("bno", service.read(bno));
		mav.addObject("cri", cri);
		mav.setViewName("/board/read");
		
		return mav;
	}
	// 삭제
	@RequestMapping(value="/board/remove", method=RequestMethod.GET)
	public String remove(@RequestParam("bno") int bno, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "삭제");
		
		return "redirect:/board/listAll";
	}
	// 수정 입력폼
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
	// 수정 완료
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
		rttr.addFlashAttribute("msg", "수정");
		
		return "redirect:/board/listAll";
	}
	// 목록 검색 기능 추가
	// 뷰에서 쿼리스트링 값으로 page값이 넘어옴
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
