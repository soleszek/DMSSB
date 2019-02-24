package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.domain.Tag;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.MessageService;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import com.oleszeksylwester.dmssb.DMSSB.utils.UserJsonSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

	private UserService userService;

	List<User> data = new ArrayList<>();

	MainController(UserService userService) {
		this.userService = userService;
/*		// init data for testing
		data.add(new Tag(1, "ruby", "sylwester"));
		data.add(new Tag(2, "rails", "anna"));
		data.add(new Tag(3, "c / c++", "krzysztof"));
		data.add(new Tag(4, ".net", "jan"));
		data.add(new Tag(5, "python", "piotr"));
		data.add(new Tag(6, "java", "alojzy"));
		data.add(new Tag(7, "javascript", "szczepan"));
		data.add(new Tag(8, "jscript", "michal"));
		data.add(new Tag(9, "go", "iza"));*/
	}

	@RequestMapping(value = "/example", method = RequestMethod.GET)
	public ModelAndView getPages() {

		ModelAndView model = new ModelAndView("example");
		return model;

	}

	@GetMapping("/getTags")
	public @ResponseBody
	List<UserJsonSearch> getTags(@RequestParam String tagName) {

		/*return simulateSearchResult(tagName);*/
		return userService.searchDynamically(tagName);

	}

/*	private List<Tag> simulateSearchResult(String tagName) {

		List<Tag> result = new ArrayList<Tag>();

		// iterate a list and filter by tagName
		for (Tag tag : data) {
			if (tag.getTagName().contains(tagName)) {
				result.add(tag);
			}
		}

		return result;
	}*/

}
