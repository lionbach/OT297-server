package com.alkemy.ong.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class MemberRequest {
    @ApiModelProperty(notes = "the member name", example = "Jhon Example", required = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String name;

    @ApiModelProperty(notes = "the facebook url", example = "facebook.com")
    private String facebookUrl;

    @ApiModelProperty(notes = "the instagram url", example = "instagram.com")
    private String instagramUrl;

    @ApiModelProperty(notes = "the linkedin url", example = "linkedin.com")
    private String linkedinUrl;

    @ApiModelProperty(notes = "the base 64 image to save in aws S3 or a false name that does not start with data:image ",
            //example = "imageCool.png",
            example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAADAFBMVEUAAAAAADMAAGYAAJkAAMwAAP8AMwAAMzMAM2YAM5kAM8wAM/8AZgAAZjMAZmYAZpkAZswAZv8AmQAAmTMAmWYAmZkAmcwAmf8AzAAAzDMAzGYAzJkAzMwAzP8A/wAA/zMA/2YA/5kA/8wA//8zAAAzADMzAGYzAJkzAMwzAP8zMwAzMzMzM2YzM5kzM8wzM/8zZgAzZjMzZmYzZpkzZswzZv8zmQAzmTMzmWYzmZkzmcwzmf8zzAAzzDMzzGYzzJkzzMwzzP8z/wAz/zMz/2Yz/5kz/8wz//9mAABmADNmAGZmAJlmAMxmAP9mMwBmMzNmM2ZmM5lmM8xmM/9mZgBmZjNmZmZmZplmZsxmZv9mmQBmmTNmmWZmmZlmmcxmmf9mzABmzDNmzGZmzJlmzMxmzP9m/wBm/zNm/2Zm/5lm/8xm//+ZAACZADOZAGaZAJmZAMyZAP+ZMwCZMzOZM2aZM5mZM8yZM/+ZZgCZZjOZZmaZZpmZZsyZZv+ZmQCZmTOZmWaZmZmZmcyZmf+ZzACZzDOZzGaZzJmZzMyZzP+Z/wCZ/zOZ/2aZ/5mZ/8yZ///MAADMADPMAGbMAJnMAMzMAP/MMwDMMzPMM2bMM5nMM8zMM//MZgDMZjPMZmbMZpnMZszMZv/MmQDMmTPMmWbMmZnMmczMmf/MzADMzDPMzGbMzJnMzMzMzP/M/wDM/zPM/2bM/5nM/8zM////AAD/ADP/AGb/AJn/AMz/AP//MwD/MzP/M2b/M5n/M8z/M///ZgD/ZjP/Zmb/Zpn/Zsz/Zv//mQD/mTP/mWb/mZn/mcz/mf//zAD/zDP/zGb/zJn/zMz/zP///wD//zP//2b//5n//8z///8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABlenwdAAABAHRSTlP///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAG8mZagAAAAlwSFlzAAAOxAAADsQBlSsOGwAACBhJREFUeJztnDmeqzgQh+2IvguOIDIv4hJM9CYaDkLou9CRD+PrjBckatGuop9mfqqsWyDVV38tJaHu0/w/sdOfdkDKKkhpVkFKswpSmlWQ0qyClGYVpDSrIKVZBSnNKkhpVkFKswpSmlWQ0qyClGYVpDSrIKVZBSnNKkhpVkFKswpSmlWQ0kwSZKQWWZ5lgiDj7U4MezrS4pskiaQiD2rI0/HGygXbFgQxOIokGe9u0EwTBGF+Ik8NnI9HiSBGR4EkBkFEJZEDMfj5HAXK05GPIFlJpECMggBJjIJISiIGYnZUSWIR5HEvDcQiiPbUxikniRSIzVG1VtiKxSSRAbH1HOWpnXOfDjJNCMTu6EcSe7GUJCIgDkHeo8A6ggQlkQFxCPL21FUsJIkEiNvRpyQuQaQkEQH5djr6MC/62r6LAXEL8uw8nnIJFyRAmCDunsTLRSSRAKGOerranc0NZYCwoXzzDH7jGyWAMEHc0/FzujW88udBTOF1SjIbFkgBSfJBaPSNv0SCzGyeE1gUc0FYcN9TkEOS9/LHJ7pskmwQkyAOSVTsxSXJBGGhv7t3hHrHyPhzSXJBbA5ZJFGRtwUg3fJA7P5YEkU9FsQlyQSxu2OUZI+7uCRZIC5vjJLAk0dhSfJAnM4YQOC7wpLkgLh9MUiCT+dlJckCoa6QZc0liAE0T5KsruX2hC3fZN9hWUsTLQPEl/oxRymoaOqYowjtOR4//aQZvmSARAtyrCQZingEMU2/lJWWpzuTDpIgyKGSpIO4O7gl/aVrhRs0xlJBzBsqUO7OftVTchusZBB3rH37EetjPw3iy5S8O0T1HJUkOU9JBXEnSr49u/3B5FGSBuIVxH7W6M1TEkkSQXzNWzm8s1uqJEkgXkFcx9jePCWNJA3E07j7g4gnA0iUJK1rZQhyVOqYAuJbxjxfqHxZcpokSYpECuLZSMpIkgDiTfWYYxQssr6jQGI3VPyr7hHZfDxIdP4eP1unSJKgSKQgd8MXrAMkiQZJEeQnJAkDGfpLc3pZc+lX2OJ0stvu87Vr29OpbSf9rl5Bh7551dxOC6h1sdTYZIL0DaquXWJA5rE/83c3SYYLeH46GOTasArbNRjkPpDXJyBJb6n2EJCLscopFOQfFoZJS9KxV9bDQGg8tbVhIHfD+4vqctZabSCXZJBfNg7V5u9Gm/q9tle5cvbc9VMLCZ99Tv3Y9dde6b4pPbXtGdmn9JoMsnNc+mGYh+H3LsGEZh/V31s0jd6Vr+9peUWSXFCUh81XPfrQDP2p3NWz3CC6E1+G98+v9WDVKAtqzQjyW3O814oFPnPCzg0QEsdIlfWpIFp8VcNnsdMkqDEjyNfnl5+XtfMtGAe6t4wdeR8ECVaTAqI67h4JPMInKL8J5Abff0nSqgjoSlgF+9v7ohkgiAtECaLV1xsqNWxnD8g49F3TdOp1N8hAQXSYmgBBXCBqxhn0b1QTanrs+dMQhGZhNyfIlYKo1HEj7GanOUAamyA6sl9uEFYjHCMTjVLHQLYjsM2PX8kgJxqJvQk1Spwg7KriuE1iC1B1F7VhIJ9IhAniAFFDRMUM5tqqb4EocRBeJfR1JdV3XNFP32yodNkgoAXqhQmEH4Zsj0yoe576VyU6FZogyGvPFiiIA+SKuw/a/ISAsBNDFRmiKrYFgTwlCRTEAaImLQWCWtjK9uRH9X8NwgVpiKvGhPOBbdzoXemiDwQrgnejXJERpR8mQRrWdwwkHQG5hQoSPEbwVpyBjDMBYYJsHl1gNbx3DQRka+hr9logCDkeYLPWeCcgVJAt3WnIMcRyeWa956bf+nFHjy7aUEFC1xHSAF9HcGbLBPlSHObz0S1oZ3resqrXskDAyk7Pc7ZA6SH4dA+DkOxkr8p4ZD3sGycMGi5IYK5F4shzLbLXuJs5htl8ZN3s2qOQRQgSlv1aBNHvsk0TFgRymA7jOugvBG21UFkg+37EMkJQFoZA7ByG81GcZYOgxQgSuEM0cuAsDIIgQQgHk0RN5XgbCgUJOj8N27PvZ52PVR+G4I0jBIF1UI6ZnoK3JO5ako2wDfuDn9BTlGlZ18e6LhoDtHwjIFAQAweWRFUIkoQ7KlnCvvO6Qaznc7Dnfjo9ADFEoruAmhpDPwWbzQ101RUGnc2nnjQ2+6J+IyDf7PiD2l+aQ03kKCn8SKIFCftcknj2CxreesIOsveEq/FdkBqqgX5GTb5Do4rY5JEKMl9b5kgDZnbV4zUIWAz5KfUGcsccp7/5DRAgSNDlJy/I09EFozTogEkNTQ0CyqwgMx7oC/+CtY+QQEn8IO/QLdsRdDstOPHRk6UCgT5ZQdCR5evjD/uo+GlOb17yQaKv/AXNldHXpPySeEEi72AG3r+IvSflr9YDcowgR0jiA4m88hd8IUa8YjfIAX1ZvSkttQck9g5m+If+2Pu2PkmcIMcJIi+JGyT2DmbMzQvPBZRYSVwgx14hSbnUkgpy7KUez1+1R0riADn6To+sJC6Qwy++eSSJCpSra0m2YzLRSNlB4pWPN1JDTt91gEQKknBdN/oCcQqI8OxobkNQErsiNBpxPgSSxK649qpsID8hiKgkVpCj8vejmrGAHLWhYu2IpaU2kJ8RRLAhW9f6xsbux5Dy5D9WGyNbutkqso6Rn/pnyrH/tjkW5D9nFaQ0qyClWQUpzSpIaVZBSrMKUppVkNKsgpRmFaQ0qyClWQUpzSpIaVZBSrMKUppVkNKsgpRmFaQ0qyClWQUpzSpIaVZBSrN/Acq90n1YF6kxAAAAAElFTkSuQmCC",
            required = true)
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank(message = "cannot be blank")
    private String image;

    @ApiModelProperty(notes = "the member description", example = "A cool member")
    private String description;
}
