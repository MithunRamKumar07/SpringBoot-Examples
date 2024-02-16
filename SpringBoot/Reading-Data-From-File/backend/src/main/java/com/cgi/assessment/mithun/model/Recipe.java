package com.cgi.assessment.mithun.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Recipe {

    String title;
    String href;
    String[] ingredients;
    String thumbnail;
}
