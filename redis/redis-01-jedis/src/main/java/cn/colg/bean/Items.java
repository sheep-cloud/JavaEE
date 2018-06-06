package cn.colg.bean;

import java.math.BigDecimal;

import cn.colg.core.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Github 接口
 *
 * @author colg
 */
@Getter
@Setter
@Accessors(chain = true)
public class Items extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String login;
    private Integer id;
    private String nodeId;
    private String avatarUrl;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private Boolean siteAdmin;
    private BigDecimal score;
}
