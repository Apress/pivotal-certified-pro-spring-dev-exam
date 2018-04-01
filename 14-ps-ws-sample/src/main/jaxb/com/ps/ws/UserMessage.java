
package com.ps.ws;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="email">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[^@]+@[^\.]+\..+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="userType" type="{http://ws-boot.com/schemas/um}userType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="active" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userMessage", namespace = "http://ws-boot.com/schemas/um", propOrder = {
    "email",
    "rating",
    "userType"
})
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class UserMessage {

    @XmlElement(namespace = "http://ws-boot.com/schemas/um", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String email;
    @XmlElement(namespace = "http://ws-boot.com/schemas/um")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected double rating;
    @XmlElement(namespace = "http://ws-boot.com/schemas/um", required = true)
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected UserType userType;
    @XmlAttribute(name = "active")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected Boolean active;

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public double getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setRating(double value) {
        this.rating = value;
    }

    /**
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link UserType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserType }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setUserType(UserType value) {
        this.userType = value;
    }

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public Boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2016-10-16T08:23:57+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setActive(Boolean value) {
        this.active = value;
    }

}
