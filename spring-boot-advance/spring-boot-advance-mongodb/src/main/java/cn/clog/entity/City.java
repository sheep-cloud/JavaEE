package cn.clog.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 城市实体类
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 城市编号 */
    private Long id;
    /** 省份编号 */
    private Long provinceId;
    /** 城市名称 */
    private String cityName;
    /** 描述 */
    private String description;

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\",\"provinceId\":\"" + provinceId + "\",\"cityName\":\"" + cityName + "\",\"description\":\"" + description + "\"}";
    }
}
