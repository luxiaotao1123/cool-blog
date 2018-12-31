package com.cool.blog.common.constant;

import com.alipay.api.AlipayConstants;


public class AlipayConfig {
    // gateway url
    public static final String URL    = "https://openapi.alipaydev.com/gateway.do";
    // 沙箱appid
    public static final String APP_ID        = "2016092300576993";
    // 私钥
    public static final String APP_PRIVATE_KEY   = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4O74qJJmeJdpOx9B3jZfq3tUM8bBLVP0L9UZ1rSsoSeH36O7mKSGoDfQQ0eY4a4enYO6pdoIADmFtAQYHX2o6Cj122+ROTUQG1K6SsvmxFM9lVJP3ab3VvoFPiGJLdqXAZFbN5SOdx5Aa2qfh9WQTvI9+1NV+Z6BqgP4KDYqcm2uQD3xKDsjKGJytldpSaK47Lb1WBZkmv0UqmsFO8nRE7Lj+6jSfw1SJqZuIakTyefLrD5nbEQj5l0UADLLlmzSJoc+XkH8/Yzov0kfmgQdDF4fhFQeOMd96EwxYmsixeKyVIxyvOg8gq2hvwTnWOfianjWrz/2gyCj1IWShe1MZAgMBAAECggEAEDqLBNFWitzQvzHQxkc9w52/bLV5jxk8dSo0+7a2X4Uql5j4OmQTJ+eawUSMIBowx+i8/UaIKxMZN+3oJwwbomM2iNrT4KXjwSpI+nkzj4rCvi1WH2w8IbD3WRcteXJJJYpxLw/pNEPZCNSMB1aDa02n4kdDSzA7rEYSmrYYqLTNzy0ay0M1m4xQbtATzDo0Zc+du3Skh0Gg+gjteb53XeNGIdWQMY85n/1RCKxI9kwfi4eV6UrtOdos8I+kjahz0odWQDDK/TcQUG7xsLbeKAiqKpR/taV1n2baRC1YvOz/Jd676dXUvcs/kItjtCrNXwyeITMmRDh8qOB2qO3E0QKBgQDmrmZiIP+AbwMahoPWSHzV0zN6b311Rwdd87zTLAuQzeJOIsn8KGz9ScpOR6P9kbf7f2ABkUhn8MGE3Ye57FFV/HFHxZMe7Qe/StV2P59BVwuvlRbHqh17iQGrapqaLBNLOIpDYldzrlHxFbULCjiSbuj0TiC1JNF0jA8tk6yURQKBgQDMdERPDAOU3E6UmCA5JliMzF9jAK2n+vBX3SmaeRwkJdFYaGaMq7B8FxP7WXXPWzX6c5LLu6rOf04nmhcgYfDkrAB4ljYsZOQU4WJcr8U4mdb6QPddBMxc/FrIhE0IxZ5QsbL6LbjxOa2G+FvnJ/QHDwSI/X1JT3PwQlpiRbTyxQKBgF/KH72z0iU08+b54JkEtMHd18yJq2Edw5Nh+RD/gzX4KHclvxY0ez2afKWQ3ioXbnzC4c0E1TpEITfY/dlYplW8ZBXXl0YKS2NF4eoedTUevj2E/Vaf72oy9DyIIfbPQpvDuLZOid4Bjwk7aFhTQDaeU6HPJ4nQ5iEtX8gfQFS5AoGBAICvxbcDVtXBfOxmfBG8owusL3W/HY6XkeOb3u3NwPq3mvbMb4+nGSRh2j/gZRiozmbfi8wUSUREOMsQX/IP83HbGUjNMzfqIO1Ll3/DWp1vpzJI5el0dbUpWXLQYzRcPnOIBaEWWkvrA9/qPt5V9XDj8PTQqKUWD2FwQjeMrIIJAoGBALHn9s4PkD2kRsGQii44D+/5EqBwrFKYw/7aVOQ9Y5jA5eZTS8lVrU6s7sPqp1pnPxmZ5UljqFrtEt9Fojls/DBPVz0ikVBB64AXsX17Jnw35kIRscKrhpcpzhTXLIPAlm/2nzfNdhIeNGRQFN3IqxuBd2gmPw1qekEoXMXy0Bl6";
    // 公钥
    public static final String APP_PUBLIC_KEY    = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuDu+KiSZniXaTsfQd42X6t7VDPGwS1T9C/VGda0rKEnh9+ju5ikhqA30ENHmOGuHp2DuqXaCAA5hbQEGB19qOgo9dtvkTk1EBtSukrL5sRTPZVST92m91b6BT4hiS3alwGRWzeUjnceQGtqn4fVkE7yPftTVfmegaoD+Cg2KnJtrkA98Sg7IyhicrZXaUmiuOy29VgWZJr9FKprBTvJ0ROy4/uo0n8NUiambiGpE8nny6w+Z2xEI+ZdFAAyy5Zs0iaHPl5B/P2M6L9JH5oEHQxeH4RUHjjHfehMMWJrIsXislSMcrzoPIKtob8E51jn4mp41q8/9oMgo9SFkoXtTGQIDAQAB";

    public static final String FORMAT      = AlipayConstants.FORMAT_JSON;
    public static final String SIGN_TYPE    = AlipayConstants.SIGN_TYPE_RSA2;

    public static final String CHARSET = "UTF-8";

    // 同步回调url
    public static final String RETURN_URL = "http://luxiaotao.top:8088/alipay_return_url";

    // 异步回调url
    public static final String NOTIFY_URL = "http://luxiaotao.top:8088/alipay_notify_url";
}
