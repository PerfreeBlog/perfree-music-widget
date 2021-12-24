package com.musicWidget.controller;

import com.perfree.base.BaseController;
import com.perfree.commons.Constants;
import com.perfree.permission.AdminMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingController extends BaseController {

    @RequestMapping("/admin/musicWidget/setting")
    @AdminMenu(groupId = Constants.ADMIN_MENU_GROUP_PLUGIN, name = "音乐播放器")
    public String setting(){
        return "/music-widget/setting.html";
    }
}
