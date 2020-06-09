package com.stv.quartzdemo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.stv.quartzdemo.helper.Ceiba2ArmaURLHelper;

@Controller
public class VideoController {

	@Autowired
	private Ceiba2ArmaURLHelper ceiba2ArmaURLHelper;

	@GetMapping("/video/{devid}")
	public String login(@PathVariable String devid, Model model) {
		Map<String, String> videos = ceiba2ArmaURLHelper.getUriliveVideosByDevid(devid);
		model.addAttribute("videos", videos);

		return "video";
	}
}