package dev.fabiou.appvox.core.review.service

import dev.fabiou.appvox.core.configuration.Configuration
import dev.fabiou.appvox.core.configuration.ProxyConfiguration
import dev.fabiou.appvox.core.review.constant.AppStoreSortType
import dev.fabiou.appvox.core.review.domain.request.AppStoreReviewRequest
import dev.fabiou.appvox.core.utils.HttpUtils
import dev.fabiou.appvox.core.utils.impl.HttpUtilsImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AppStoreReviewServiceTest {

    @Mock
    private var httpUtils: HttpUtils = HttpUtilsImpl

    @InjectMocks
    private var service = AppStoreReviewService(Configuration(requestDelay = 3000L))

    @ParameterizedTest
    @CsvSource(
        "333903271, us, 10"
    )
    fun `Get app store reviews`(
        appId : String, region : String, requestedSize : Int) {
        val mockBearerToken = "mock-bearer-token"
        val bearerTokenRequestUrl = AppStoreReviewService.APP_HP_URL_PATTERN.format(region, appId)
        Mockito.doReturn(mockBearerToken)
                .`when`(httpUtils).getRequest(
                        requestUrl = bearerTokenRequestUrl,
                        bearerToken = null,
                        proxyConfig = null)

        Mockito.doReturn("{\"next\":\"/v1/catalog/us/apps/333903271/reviews?offset=20\",\"data\":[{\"id\":\"5210384573\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":5,\"review\":\"Watching videos on this is so frustrating sometimes because if you try to skip ahead into a video it will just freeze the video and remain frozen until the amount of time you skipped ahead is reached and then it will resume. I know I can unfreeze it by moving the little dial at the bottom to skip through the video but i shouldn’t have to do that. Also i’ve had sound glitches where if you try to skip ahead or rewatch a video once it reaches the end the sound won’t play and i just move the little bar at the bottom to skip through the video left and right until the sound comes back. It’s pretty annoying and happens literally all the time. Fix it bruh y’all millionaires. Instagram does better with their videos like cmon son and I know i’m not the only one experiencing this all my friends get it too. Other than this the app is fine\",\"isEdited\":false,\"date\":\"2019-11-29T04:56:56Z\",\"userName\":\"daniel___9898\",\"title\":\"Videos are glitchy\"}},{\"id\":\"5079513114\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":5,\"review\":\"It’s a lot better than Facebook, Snapchat, Instagram, Tumbler, Tic-Toc, or anything else. The reason being that Twitter is for the highly intelligent people as philosophers and tweet out their ideas. Scientists can throw out new discoveries in 280 characters or less. There is a wealth of fact accounts which tweet old facts. Bots that do what ever that not was programmed to do. There are many webcomic artists and artists in general. News travels quicker around the country or world. Movie trailers, game trailers, show trailers, et cetra. This was my first social to media to own an account on, and for that reason it will be the most idealistic way to experience it. Trust me I’ve been way more active in my iPhone’s account than my PC’s account. My PC’s account was my original while my iPhone’s account came second. Despite this you shouldn’t need any other social media to experience one like this.\",\"isEdited\":false,\"date\":\"2019-11-03T14:11:46Z\",\"userName\":\"Devilthorn135\",\"title\":\"Opinion is mine.\"}},{\"id\":\"6869350177\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":1,\"review\":\"It truly sad how much Twitter uses it’s platform to push the Democratic Party and at the same time destroy the Republican Party. Social media should be about what ever people want to talk about, good or bad, if Twitter agrees or not. Any social media platform should be neutral and let it’s users say and do as ever they want up to a point. Furthermore to block someone like the president of the United States, republican or democrat, is not right at all, just because Twitter leans towards the Democratic Party is not a smart or good reason to block the president of the United States. With that being said it’s obvious Twitter will only allow its users to see what Twitter believes in and will allow its users to see. This platform does not allow people to view both sides and make up a decision for them selves. Instead Twitter thinks it’s best to think for the people and do their best to influence a certain party or idea based on what ever Twitter believes. I really hope one day Twitter can be a neutral social media platform. It’s truly sad and beyond unprofessional for a company like Twitter to do something so embarrassing and damaging to their own company based on personal beliefs.\",\"isEdited\":false,\"date\":\"2021-01-14T16:29:55Z\",\"userName\":\"B.J.86\",\"title\":\"One sided\"}},{\"id\":\"3359951690\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":3,\"review\":\"Every time I try to takeoff the sensitive mode it doesn’t wanna take it off. I tried doing the instructions on Google I’ve tried even just going through all the settings and change and everything differently and that’s not the half of it, I’ve  had my account blocked or suspended just for following people I’ve been suspended just for a re-tweeting a tweet I’ve had so many problems with it and I just got it this month and it’s really aggravating and I had a couple of accounts but they were so long ago that I didn’t even remember the password to them and it was so hard just for me to delete them like I had to have a phone number I had to have a PIN number  or just something that was just dumb and stupid and wouldn’t just let me delete the accounts. But I successfully deleted them but I’m still having trouble with the sensitivity and I would really appreciated if you would fix it or something because I’ve tried literally everything.\",\"isEdited\":false,\"date\":\"2018-10-30T05:21:06Z\",\"userName\":\"Nunu20026 on Twitter\",\"title\":\"I’m really irritated at this point!!\"}},{\"id\":\"6551859561\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":1,\"review\":\"When scrolling down at a certain point it just stops and doesn’t display any more posts. Often a post I tap on disappears or the app will scroll to the top after I tap on the post and I am unable to find that post again. Back-end administration is not even handed and is shrouded in secrecy. For example, blue star “verification” is a joke as many easily verified legitimate sources aren’t verified even though they apply when other accounts favored by those in control get verified immediately. Posts are not handled evenly either. For example, Policies are a joke and are applied unevenly to posts those in control don’t like but not to posts they do like. Terms of Service also seem to be applied unevenly. If enough of a certain group of users complain they will remove or restrict posts and or users based on mob mentality. Certain Chronic violators are allowed to continue on the platform but others are booted off for much lesser infractions and it is usually around political views. If this is a forum for free speech then the powers that be are doing a bad job of it. I’m going to use the Parler app.\",\"isEdited\":false,\"date\":\"2020-10-19T15:28:43Z\",\"userName\":\"johnghicks\",\"title\":\"Bad for my blood pressure\"}},{\"id\":\"4211245767\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":1,\"review\":\"Like the title says they don’t actually do any work into moderating their site. You might think there’s someone out there reading the tweets that go out but if you search specific slurs you’ll find a myriad of tweets with the n word or f word in them and twitter doesn’t bat an eyelash but get into a discussion with a pedophiliac apologist and tell them they are trash for condoning cp art well then they’re ready to ban or suspend your account. But to be perfectly honest I’m ok being banned from a site that offers a platform to racists, nazis, pedophiles, transphobia, and the absolute cesspool of society. I’m actually glad this has happened and made me realize what a piece of garbage this platform is. It might seem better than the tumblrs of the world and even facebook but is a smidgen better when it comes to social media? Just because it gives the appearance of giving a crap doesn’t mean it does and believe me, Twitter is no different than the facebooks of the world. They would sell you D list celebs selling flat tummy tea but you just have to have targeted ads on. Great service for a dumpster fire I guess...\",\"isEdited\":false,\"date\":\"2019-05-26T05:54:38Z\",\"userName\":\"chichikamish\",\"title\":\"They don’t care and it shows lol\"}},{\"id\":\"6526359590\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":4,\"review\":\"I love twitter and have always loved twitter until they updated their rules and policies. they are too strict for my linking now. i’m not understanding how tweeting in bold texts or following/unfollowing too many ppl at once or even tweeting how you feel about a certain political issue can be looked at as a threat. i understand if someone is giving misleading information or threatening someone else but some of their rules just doesn’t make any sense and is uncalled for. Also, i don’t like how you can’t use the same number for multiple accounts. It also says you can in their rules and policies but it won’t let you do it and they need to take that out of their rules so it won’t be misleading. i don’t like having to tweet and then delete my tweets cause i’m overthinking that it’s against twitters rules when it’s not. i respect their rules and policies but i just feel some of them are too strict and not really harmful to be considered a threat.\",\"isEdited\":false,\"date\":\"2020-10-12T06:04:07Z\",\"userName\":\"ABHudson234\",\"title\":\"Opinion\"}},{\"id\":\"6872825295\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":1,\"review\":\"Twitter didn’t used to be this way. They’ve seem to have lost their complete minds as soon as the 2020 presidential election took place and haven’t recovered since. They have since been exposed for not just going after the president of the United States, but hundreds of thousands of conservative accounts or accounts that dare question the popular media narrative. Let me be very clear. What is popular does not equal what is right. There is plenty of wrongdoing on all sides of the aisle. To censor the right for incidents I can cite for the left as well is inappropriate, a threat to our democracy, and should make us all question where twitter stands as a platform that wouldn’t be who they are today if it wasn’t for the people (us). This is clear by the billions twitter is loosing in the stock market as we speak. Let the people speak or we will not stick around. Twitter should not be allowed more power than all branches of our government in respect to issues like this. My account is permanently deactivated for my lifetime, unless twitter rights it’s wrongs and changes ownership. Until then, get wrecked in the stocks! GOD BLESS AMERICA!!!!\",\"isEdited\":false,\"date\":\"2021-01-15T14:21:04Z\",\"userName\":\"VickieToriii\",\"title\":\"Censorship Galore\"}},{\"id\":\"5805020478\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":2,\"review\":\"Twitter is a left wing bias site. I’m Independent and see how bad it has gotten. When will or will it ever be a fair platform? I’m currently on a 3 day limited restriction but have no idea why! I haven’t broken any Twitter rules! I get those captchas asking if I’m a bot but I do everything possible not to do repetitive things just for this reason. I put out some interesting tweets that went a little viral by maybe 1,000 and because I got a lot of followers in a day I go to Twitter jail? Really? I already only follow back so many at a time so I don’t get in trouble but you find a reason anyway and don’t tell me why! I hope one day someone creates a site that is fair to its users. I’m never threatening in any way but Twitter likes to restrict me for no good reason! I mostly just tweet what’s out on another source so why am I in trouble? I’ve seen people threatening on the left but never get in trouble! Hopefully one day someone will come up with a less bias and Constitutional platform. I believe in Free Speech but would never threaten anyone but I see it all the time!\",\"isEdited\":false,\"date\":\"2020-04-13T11:00:56Z\",\"userName\":\"Patty Angel\",\"title\":\"Bias\"}},{\"id\":\"4524035087\",\"type\":\"user-reviews\",\"attributes\":{\"rating\":1,\"review\":\"Twitter oh twitter. You have the worst people in your company and those people are radical liberals who think they can silence whoever they want by suspending their account. My account recently got suspended for no reason at all. All I did was respond to a African American woman’s tweet. Mind you that I am also African American. Twitter suspended me because apparently I was being hateful and harassed this person. When obviously I was responding to a situation with a young rapper was charged for a crime and how it’s terrible that youth are on the wrong path to success when it comes to rapping. But because of the word I used which was n***a a word I often used on the platform before my suspension, but because I used it and it was in response to a African American woman. Twitter’s most likely algorithm picked my tweet and most likely thought I was being racist. Twitter you need to think about what you’re doing because you literally just disobeyed the first constitutional right which is free speech. Stop trying to be the good guys and focus on the company and brand. Stop with the obvious social justice warrior nonsense.\",\"isEdited\":false,\"date\":\"2019-07-27T06:17:23Z\",\"userName\":\"Varis09245\",\"title\":\"They suspend accounts for no reason\"}}]}")
                .`when`(httpUtils).getRequest(
                    requestUrl = ArgumentMatchers.anyString(),
                    bearerToken = ArgumentMatchers.anyString(),
                    proxyConfig = ArgumentMatchers.any())

        val bearerToken = service.getBearerToken(
            appId = appId,
            region = region
        )
        val request = AppStoreReviewRequest(
                region = region,
                sortType = AppStoreSortType.RELEVANT,
                bearerToken = bearerToken
        )

        val response = service.getReviewsByAppId(appId, request)

        Assertions.assertEquals(requestedSize, response.data.size)
    }
}