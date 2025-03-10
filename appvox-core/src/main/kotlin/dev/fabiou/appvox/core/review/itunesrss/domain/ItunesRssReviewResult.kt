package dev.fabiou.appvox.core.review.itunesrss.domain

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlSchemaType
import javax.xml.bind.annotation.XmlValue
import javax.xml.datatype.XMLGregorianCalendar

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = ["id", "title", "updated", "link", "icon", "author", "rights", "entry"])
@XmlRootElement(name = "feed")
internal data class ItunesRssReviewResult(

    @XmlAttribute(name = "xmlns")
    val uri: String? = null,

    @XmlElement(required = true)
    val id: String? = null,

    @XmlElement(required = true)
    val title: String? = null,

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    val updated: XMLGregorianCalendar? = null,

    @XmlElement(required = true)
    val link: List<Link>? = null,

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    val icon: String? = null,

    @XmlElement(required = true)
    val author: Author? = null,

    @XmlElement(required = true)
    val rights: String? = null,

    @XmlElement(required = true)
    val entry: List<Entry>? = null
) {

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(
        name = "", propOrder = [
            "updated", "id", "title", "content", "voteSum", "voteCount", "rating", "version", "author", "link"]
    )
    @XmlRootElement(name = "entry")
    internal data class Entry(
        @XmlElement(required = true)
        @XmlSchemaType(name = "dateTime")
        val updated: XMLGregorianCalendar? = null,

        @XmlElement(required = true)
        val id: String? = null,

        @XmlElement(required = true)
        val title: String? = null,

        @XmlElement(required = true)
        val content: List<Content>? = null,

        @XmlElement(required = true, name = "im:voteSum")
        val voteSum: Int? = 0,

        @XmlElement(required = true, name = "im:voteCount")
        val voteCount: Int? = 0,

        @XmlElement(required = true, name = "im:rating")
        val rating: Int? = 0,

        @XmlElement(required = true, name = "im:version")
        val version: String? = null,

        @XmlElement(required = true)
        val author: Author? = null,

        @XmlElement(required = true)
        val link: Link? = null
    ) {
        @XmlRootElement(name = "content")
        @XmlAccessorType(XmlAccessType.FIELD)
        internal data class Content(
            @XmlValue
            val content: String? = null,

            @XmlAttribute
            val type: String? = null
        )
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = ["value"])
    @XmlRootElement(name = "link")
    internal data class Link(
        @XmlValue
        val value: String? = null,

        @XmlAttribute(name = "rel")
        val rel: String? = null,

        @XmlAttribute(name = "href")
        val href: String? = null,

        @XmlAttribute(name = "type")
        val type: String? = null,
    )

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = ["name", "uri"])
    @XmlRootElement(name = "author")
    internal data class Author(
        @XmlElement(required = true)
        val name: String? = null,

        @XmlElement(required = true)
        val uri: String? = null
    )
}
