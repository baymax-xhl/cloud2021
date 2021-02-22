package com.baymax.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/2/22 12:39 下午
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment implements Serializable {
  private Long id;
  private String serial;
}
