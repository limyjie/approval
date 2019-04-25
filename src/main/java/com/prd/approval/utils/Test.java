/**
 * Author: lin
 * Date: 2019/4/22 20:17
 */
package com.prd.approval.utils;

import java.util.Optional;

/**
 *<p></p>
 *
 */
public class Test {

    public static void main(String[] args) {
        Optional<String> optionalString = Optional.of("input");
        System.out.println(optionalString.map(Test::getOutputOpt));
        System.out.println(optionalString.flatMap(Test::getOutputOpt));
    }

    private static Optional<String> getOutputOpt(String input) {
        return input == null ? Optional.empty() : Optional.of("output for " + input);
    }

    private static String getOutput(String input) {
        return input == null ? null : "output for " + input;
    }
    //这两种返回的结果是一样的
}
