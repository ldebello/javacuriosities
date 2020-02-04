
package ar.com.javacurisioties.jaxws.client.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.javacurisioties.jaxws.client.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Say_QNAME = new QName("http://sei.jaxws.javacurisioties.com.ar/", "say");
    private final static QName _Say0_QNAME = new QName("http://sei.jaxws.javacurisioties.com.ar/", "say0");
    private final static QName _Say3_QNAME = new QName("http://sei.jaxws.javacurisioties.com.ar/", "say3");
    private final static QName _Say3Response_QNAME = new QName("http://sei.jaxws.javacurisioties.com.ar/", "say3Response");
    private final static QName _SayResponse_QNAME = new QName("http://sei.jaxws.javacurisioties.com.ar/", "sayResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.javacurisioties.jaxws.client.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Say }
     * 
     */
    public Say createSay() {
        return new Say();
    }

    /**
     * Create an instance of {@link Say0 }
     * 
     */
    public Say0 createSay0() {
        return new Say0();
    }

    /**
     * Create an instance of {@link Say3 }
     * 
     */
    public Say3 createSay3() {
        return new Say3();
    }

    /**
     * Create an instance of {@link Say3Response }
     * 
     */
    public Say3Response createSay3Response() {
        return new Say3Response();
    }

    /**
     * Create an instance of {@link SayResponse }
     * 
     */
    public SayResponse createSayResponse() {
        return new SayResponse();
    }

    /**
     * Create an instance of {@link SayParameters }
     * 
     */
    public SayParameters createSayParameters() {
        return new SayParameters();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Say }{@code >}
     */
    @XmlElementDecl(namespace = "http://sei.jaxws.javacurisioties.com.ar/", name = "say")
    public JAXBElement<Say> createSay(Say value) {
        return new JAXBElement<Say>(_Say_QNAME, Say.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say0 }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Say0 }{@code >}
     */
    @XmlElementDecl(namespace = "http://sei.jaxws.javacurisioties.com.ar/", name = "say0")
    public JAXBElement<Say0> createSay0(Say0 value) {
        return new JAXBElement<Say0>(_Say0_QNAME, Say0 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say3 }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Say3 }{@code >}
     */
    @XmlElementDecl(namespace = "http://sei.jaxws.javacurisioties.com.ar/", name = "say3")
    public JAXBElement<Say3> createSay3(Say3 value) {
        return new JAXBElement<Say3>(_Say3_QNAME, Say3 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say3Response }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Say3Response }{@code >}
     */
    @XmlElementDecl(namespace = "http://sei.jaxws.javacurisioties.com.ar/", name = "say3Response")
    public JAXBElement<Say3Response> createSay3Response(Say3Response value) {
        return new JAXBElement<Say3Response>(_Say3Response_QNAME, Say3Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SayResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://sei.jaxws.javacurisioties.com.ar/", name = "sayResponse")
    public JAXBElement<SayResponse> createSayResponse(SayResponse value) {
        return new JAXBElement<SayResponse>(_SayResponse_QNAME, SayResponse.class, null, value);
    }

}
