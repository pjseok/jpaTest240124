package com.pjseok.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pjseok.home.entity.Member;
import com.pjseok.home.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class JpaController {

	@Autowired
	MemberRepository memberRepository;
	
	@RequestMapping("/memberlist")
	public String memberlist(Model model) {
		
		List<Member> members = memberRepository.findAll();
		
		model.addAttribute("memberlist", members);
		
		return "memberlist";
	}
	@RequestMapping("/memberjoin")
	public String memberjoin(Model model) {
		
		
		
		return "memberjoin";
	}
	
	@RequestMapping("/memberdel")
	public String memberdel(Model model) {
		
		
		
		return "memberdel";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request) {
		
		Long mid =Long.parseLong(request.getParameter("membernum"));
		
		memberRepository.deleteById(mid);
		
		return "redirect:memberlist";
	}
	
	@RequestMapping("/join")
	public String join(HttpServletRequest request) {
		
		String mid = request.getParameter("memberid");
		String mpw = request.getParameter("memberpw");
		String mname = request.getParameter("membername");
		int mage =Integer.parseInt(request.getParameter("memberage"));
		
		Member member = new Member();
		member.setMemberid(mid);
		member.setMemberpw(mpw);
		member.setMembername(mname);
		member.setMemberage(mage);
		
		memberRepository.save(member);
		
		return "redirect:memberlist";
	}
}
