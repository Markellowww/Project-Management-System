package com.markelloww.projectmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Markelloww
 */

@Data
@AllArgsConstructor
public class Project {
    private Long id;
    private String title;
    private String description;
    private int date;
}
