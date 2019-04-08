/**
 * Author: lin
 * Date: 2019/4/6 17:11
 */
package com.prd.approval.controller;

import com.prd.approval.entity.Event;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *<p></p>
 *
 */

@RestController
@RequestMapping("/template")
public class TemplateController {

    @PostMapping("/add")
    public ResponseUtil<Event> add(@RequestBody Event event){

        return null;
    }
}
