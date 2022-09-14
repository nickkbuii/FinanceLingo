package Lessons;

import java.util.ArrayList;

public class Taxes {

    public String[] prompts = {
            "A ____ for the ____ of a ____ required of persons, groups, or businesses within the domain of that government.",
            "Taxes can be applied to almost anything ____ or owned. The most common types of taxes are ____ and ____ tax. ",
            "Taxes pay for the ____ and ____ we all use daily. For example, state taxes pay for public schools, roads, law enforcement, and emergency response. Also, evading taxes is a ____ offense. ",
            "Taxes are charged on your ____ income. The rate depends on your ____ but this tax goes to the ____ and federal government.",
            "Taxes are charged on every ____ made. The rate ____ by county but in California, expect tax rates to be at least ____.",
    };


    public String[][] answers = {
            {"contribution", "support", "government"},
            {"purchased, income, sales"},
            {"purchased, sales, income"},
            {"infrastructure, services, punishable"},
            {"services", "infrastructure", "punishable"},
            {"total", "income", "state"}
    };

    public String[][] options = {
            {"government", "contribution", "support"},
            {"sales", "purchased", "income"},
            {"punishable", "services", "infrastructure"},
            {"state", "total", "income"},
    };


}
