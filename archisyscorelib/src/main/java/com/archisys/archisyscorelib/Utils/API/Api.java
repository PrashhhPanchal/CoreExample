package com.archisys.archisyscorelib.Utils.API;

public class Api {

    // ************* API  Start ************
    //Base Url
    public static String BaseUrl="http://findmytrade.archisys.biz";
    //public static String BaseUrl= "http://195.168.0.111:5002";

    public static final String AppFirstStart = "/api/v1/Auth/AppFirstStart";
    public static final String Login= "/api/v1/Auth/Login";
    public static final String SignUp= "/api/v1/Auth/SignUp";
    public static final String ValidateOTP= "/api/v1/Auth/ValidateOTP";
    public static final String ResendOTP= "/api/v1/Auth/ResendOTP";
    public static final String RefreshToken= "/api/v1/Auth/RefreshToken";
    public static final String GetTip = "/api/Tip/GetTip";
    public static final String GetMyTip = "/api/Tip/GetMyTips";
    public static final String GetTipById = "/api/Tip/GetTipById";
    public static final String GetTipByAdvisorId = "/api/Tip/GetTipByAdvisorId";
    public static final String CommentPost = "/api/Tip/PostComment";
    public static final String PostTipLike = "/api/Tip/PostTipLike";
    public static final String PostTipDisLike = "/api/Tip/PostTipDisLike";
    public static final String GetCommentByTipId = "/api/Tip/GetCommentByTipId";
    public static final String PostCommentReply = "/api/Tip/PostCommentReplay";
    public static final String ReportAbuseForComment = "/api/v1/Application/ReportAbuseForComment";
    public static final String PostCommentLike = "/api/Tip/PostCommentLike";
    public static final String DeleteCommentById = "/api/Tip/DeleteCommentById";
    public static final String DeleteReplyById = "/api/Tip/DeleteReplayById";
    public static final String GetAllAdvisor = "/api/v1/Adviser/GetAllAdvisor";
    public static final String GetAdvisorById = "/api/v1/Adviser/GetAdvisorById";
    public static final String GetReviewRatingByAdvisorId = "/api/v1/Adviser/GetReviewRatingByAdvisorId";
    public static final String PostReview = "/api/v1/Adviser/PostAdvisorReviewRating";
    public static final String FollowUnFollowAdvisor = "/api/v1/Adviser/FollowUnfollowAdvisor";
    public static final String ReportAbuseForAdvisor = "/api/v1/Application/ReportAbuseForAdvisor";
    public static final String GetProPlanbyAdvisorId = "/api/ProPlan/GetProplanByAdvisorId";
    public static final String GetUserProfile = "/api/AppUser/GetUserProfile";
    public static final String UpdateProfile = "/api/AppUser/UpdateProfile";
    public static final String GetAdvertisement = "/api/v1/Advertisements/GetAdvertisements";
    public static final String GetStaticContentForAdvisor = "/api/v1/Application/GetStaticContentByAdvisorId";
    public static final String GetStaticContentForApp = "/api/v1/Application/GetStaticContentForAdmin";
    public static final String GetPaymentHistory = "/api/v1/PlanPurchase/GetPaymentHistory";
    public static final String GetSubscribedAdvisor = "/api/v1/Adviser/GetSubscribedAdvisor";
    public static final String CreateSubscription = "/api/v1/PlanPurchase/CreateSubscription";
    public static final String GetPromoCodebyId = "/api/v1/PromoCode/GetPromocodeById";
    public static final String ValidatePromoCodes = "/api/v1/PlanPurchase/ValidatePromocode";
    public static final String RemovePromoCode = "/api/v1/PromoCode/RemovePromocode";
    public static final String HitFlopTipsExplore = "/api/Tip/HitFlopTipsExplore";
    public static final String HitFlopTipsWatchlist = "/api/Tip/HitFlopTipsWatchlist";
    public static final String GetGlobalSettings = "/api/v1/Application/GetGlobalSetting";
    public static final String GetAdvisorSettings = "/api/v1/Application/GetAdvisorSetting";
    public static final String UpdateGlobalSettings = "/api/v1/Application/UpdateGlobalSetting";
    public static final String UpdateAdvisorSettings = "/api/v1/Application/UpdateAdvisorSetting";

    // ************* API END ***************

    //  ************ POLICY URL Start************
    //public static final String LIVE_URL_SBIQPOLICY = "http://api.stockbook.net/v1/uploads/SB_IQ_Policy.pdf";
    //public static final String LIVE_URL_TERMSNCONDITION = "http://api.stockbook.net/v1/uploads/terms_and_conditions.pdf";
    //public static final String LIVE_URL_PRIVACYPOLICY = "http://api.stockbook.net/v1/uploads/PRIVACY_POLICY.pdf";

    public static final String LIVE_URL_TERMSNCONDITION = "http://www.google.com";
    public static final String LIVE_URL_PRIVACYPOLICY = "http://www.google.com";
    //  ************ POLICY URL END ************


    //SignUp API Const
    public static final String ISDCode= "ISDCode";
    public static final String Mobile= "Mobile";
    public static final String Email= "Email";
    public static final String Name= "Name";

    //OTP API Const
    public static final String iSDCode= "isdCode";
    public static final String mobile= "mobile";
    public static final String code= "code";
    public static final String email= "email";
    public static final String name= "name";

    //Methods Name
    public static final String DELETE = "DELETE";
}
