package org.es4j.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/*
public class Guid {
    public static final Guid Empty = new Guid("");

    //private final Guid uuid;

    public static Guid newGuid() {
        if(true) throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    public Guid() {
        //uuid = Guid.randomUUID();
    }

    private Guid(String string) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
*/




/**
 * A class that represents an immutable universally unique identifier (Guid).
 * A Guid represents a 128-bit value.
 *
 * <p> There exist different variants of these global identifiers.  The methods
 * of this class are for manipulating the Leach-Salz variant, although the
 * constructors allow the creation of any variant of Guid (described below).
 *
 * <p> The layout of a variant 2 (Leach-Salz) Guid is as follows:
 *
 * The most significant long consists of the following unsigned fields:
 * <pre>
 * 0xFFFFFFFF00000000 time_low
 * 0x00000000FFFF0000 time_mid
 * 0x000000000000F000 version
 * 0x0000000000000FFF time_hi
 * </pre>
 * The least significant long consists of the following unsigned fields:
 * <pre>
 * 0xC000000000000000 variant
 * 0x3FFF000000000000 clock_seq
 * 0x0000FFFFFFFFFFFF node
 * </pre>
 *
 * <p> The variant field contains a value which identifies the layout of the
 * {@code Guid}.  The bit layout described above is valid only for a {@code
 * Guid} with a variant value of 2, which indicates the Leach-Salz variant.
 *
 * <p> The version field holds a value that describes the type of this {@code
 * Guid}.  There are four different basic types of UUIDs: time-based, DCE
 * security, name-based, and randomly generated UUIDs.  These types have a
 * version value of 1, 2, 3 and 4, respectively.
 *
 * <p> For more information including algorithms used to create {@code Guid}s,
 * see <a href="http://www.ietf.org/rfc/rfc4122.txt"> <i>RFC&nbsp;4122: A
 * Universally Unique IDentifier (Guid) URN Namespace</i></a>, section 4.2
 * &quot;Algorithms for Creating a Time-Based Guid&quot;.
 *
 * @since   1.5
 */
public final class Guid implements Serializable, Comparable<Guid> {

    public static final Guid empty = Guid.fromString("00000000-0000-0000-0000-000000000000");

    /**
     * Explicit serialVersionUID for interoperability.
     */
    private static final long serialVersionUID = -4856846361193249489L;

    public static Guid empty() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /*
     * The most significant 64 bits of this Guid.
     *
     * @serial
     */
    private final long mostSigBits;

    /*
     * The least significant 64 bits of this Guid.
     *
     * @serial
     */
    private final long leastSigBits;

    /*
     * The random number generator used by this class to create random
     * based UUIDs.
     */
    private static volatile SecureRandom numberGenerator = null;

    // Constructors and Factories

    /*
     * Private constructor which uses a byte array to construct the new Guid.
     */
    private Guid(byte[] data) {
        long msb = 0;
        long lsb = 0;
        assert data.length == 16 : "data must be 16 bytes in length";
        for (int i=0; i<8; i++)
            msb = (msb << 8) | (data[i] & 0xff);
        for (int i=8; i<16; i++)
            lsb = (lsb << 8) | (data[i] & 0xff);
        this.mostSigBits = msb;
        this.leastSigBits = lsb;
    }

    /**
     * Constructs a new {@code Guid} using the specified data.  {@code
     * mostSigBits} is used for the most significant 64 bits of the {@code
     * Guid} and {@code leastSigBits} becomes the least significant 64 bits of
     * the {@code Guid}.
     *
     * @param  mostSigBits
     *         The most significant bits of the {@code Guid}
     *
     * @param  leastSigBits
     *         The least significant bits of the {@code Guid}
     */
    public Guid(long mostSigBits, long leastSigBits) {
        this.mostSigBits = mostSigBits;
        this.leastSigBits = leastSigBits;
    }

    /**
     * Static factory to retrieve a type 4 (pseudo randomly generated) Guid.
     *
     * The {@code Guid} is generated using a cryptographically strong pseudo
     * random number generator.
     *
     * @return  A randomly generated {@code Guid}
     */
    private static Guid randomUUID() {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }

        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6]  &= 0x0f;  /* clear version        */
        randomBytes[6]  |= 0x40;  /* set to version 4     */
        randomBytes[8]  &= 0x3f;  /* clear variant        */
        randomBytes[8]  |= 0x80;  /* set to IETF variant  */
        return new Guid(randomBytes);
    }
    public static Guid newGuid() {
        return randomUUID();
    }

    /**
     * Static factory to retrieve a type 3 (name based) {@code Guid} based on
     * the specified byte array.
     *
     * @param  name
     *         A byte array to be used to construct a {@code Guid}
     *
     * @return  A {@code Guid} generated from the specified array
     */
    public static Guid nameUUIDFromBytes(byte[] name) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("MD5 not supported");
        }
        byte[] md5Bytes = md.digest(name);
        md5Bytes[6]  &= 0x0f;  /* clear version        */
        md5Bytes[6]  |= 0x30;  /* set to version 3     */
        md5Bytes[8]  &= 0x3f;  /* clear variant        */
        md5Bytes[8]  |= 0x80;  /* set to IETF variant  */
        return new Guid(md5Bytes);
    }

    /**
     * Creates a {@code Guid} from the string standard representation as
     * described in the {@link #toString} method.
     *
     * @param  name
     *         A string that specifies a {@code Guid}
     *
     * @return  A {@code Guid} with the specified value
     *
     * @throws  IllegalArgumentException
     *          If name does not conform to the string representation as
     *          described in {@link #toString}
     *
     */
    public static Guid fromString(String name) {
        String[] components = name.split("-");
        if (components.length != 5)
            throw new IllegalArgumentException("Invalid Guid string: "+name);
        for (int i=0; i<5; i++)
            components[i] = "0x"+components[i];

        long mostSigBits = Long.decode(components[0]).longValue();
        mostSigBits <<= 16;
        mostSigBits |= Long.decode(components[1]).longValue();
        mostSigBits <<= 16;
        mostSigBits |= Long.decode(components[2]).longValue();

        long leastSigBits = Long.decode(components[3]).longValue();
        leastSigBits <<= 48;
        leastSigBits |= Long.decode(components[4]).longValue();

        return new Guid(mostSigBits, leastSigBits);
    }

    // Field Accessor Methods

    /**
     * Returns the least significant 64 bits of this Guid's 128 bit value.
     *
     * @return  The least significant 64 bits of this Guid's 128 bit value
     */
    public long getLeastSignificantBits() {
        return leastSigBits;
    }

    /**
     * Returns the most significant 64 bits of this Guid's 128 bit value.
     *
     * @return  The most significant 64 bits of this Guid's 128 bit value
     */
    public long getMostSignificantBits() {
        return mostSigBits;
    }

    /**
     * The version number associated with this {@code Guid}.  The version
     * number describes how this {@code Guid} was generated.
     *
     * The version number has the following meaning:
     * <p><ul>
     * <li>1    Time-based Guid
     * <li>2    DCE security Guid
     * <li>3    Name-based Guid
     * <li>4    Randomly generated Guid
     * </ul>
     *
     * @return  The version number of this {@code Guid}
     */
    public int version() {
        // Version is bits masked by 0x000000000000F000 in MS long
        return (int)((mostSigBits >> 12) & 0x0f);
    }

    /**
     * The variant number associated with this {@code Guid}.  The variant
     * number describes the layout of the {@code Guid}.
     *
     * The variant number has the following meaning:
     * <p><ul>
     * <li>0    Reserved for NCS backward compatibility
     * <li>2    The Leach-Salz variant (used by this class)
     * <li>6    Reserved, Microsoft Corporation backward compatibility
     * <li>7    Reserved for future definition
     * </ul>
     *
     * @return  The variant number of this {@code Guid}
     */
    public int variant() {
        // This field is composed of a varying number of bits.
        // 0    -    -    Reserved for NCS backward compatibility
        // 1    0    -    The Leach-Salz variant (used by this class)
        // 1    1    0    Reserved, Microsoft backward compatibility
        // 1    1    1    Reserved for future definition.
        return (int) ((leastSigBits >>> (64 - (leastSigBits >>> 62)))
                      & (leastSigBits >> 63));
    }

    /**
     * The timestamp value associated with this Guid.
     *
     * <p> The 60 bit timestamp value is constructed from the time_low,
     * time_mid, and time_hi fields of this {@code Guid}.  The resulting
     * timestamp is measured in 100-nanosecond units since midnight,
     * October 15, 1582 UTC.
     *
     * <p> The timestamp value is only meaningful in a time-based Guid, which
     * has version type 1.  If this {@code Guid} is not a time-based Guid then
     * this method throws UnsupportedOperationException.
     *
     * @throws UnsupportedOperationException
     *         If this Guid is not a version 1 Guid
     */
    public long timestamp() {
        if (version() != 1) {
            throw new UnsupportedOperationException("Not a time-based Guid");
        }

        return (mostSigBits & 0x0FFFL) << 48
             | ((mostSigBits >> 16) & 0x0FFFFL) << 32
             | mostSigBits >>> 32;
    }

    /**
     * The clock sequence value associated with this Guid.
     *
     * <p> The 14 bit clock sequence value is constructed from the clock
     * sequence field of this Guid.  The clock sequence field is used to
     * guarantee temporal uniqueness in a time-based Guid.
     *
     * <p> The {@code clockSequence} value is only meaningful in a time-based
     * Guid, which has version type 1.  If this Guid is not a time-based Guid
     * then this method throws UnsupportedOperationException.
     *
     * @return  The clock sequence of this {@code Guid}
     *
     * @throws  UnsupportedOperationException
     *          If this Guid is not a version 1 Guid
     */
    public int clockSequence() {
        if (version() != 1) {
            throw new UnsupportedOperationException("Not a time-based Guid");
        }

        return (int)((leastSigBits & 0x3FFF000000000000L) >>> 48);
    }

    /**
     * The node value associated with this Guid.
     *
     * <p> The 48 bit node value is constructed from the node field of this
     * Guid.  This field is intended to hold the IEEE 802 address of the machine
     * that generated this Guid to guarantee spatial uniqueness.
     *
     * <p> The node value is only meaningful in a time-based Guid, which has
     * version type 1.  If this Guid is not a time-based Guid then this method
     * throws UnsupportedOperationException.
     *
     * @return  The node value of this {@code Guid}
     *
     * @throws  UnsupportedOperationException
     *          If this Guid is not a version 1 Guid
     */
    public long node() {
        if (version() != 1) {
            throw new UnsupportedOperationException("Not a time-based Guid");
        }

        return leastSigBits & 0x0000FFFFFFFFFFFFL;
    }

    // Object Inherited Methods

    /**
     * Returns a {@code String} object representing this {@code Guid}.
     *
     * <p> The Guid string representation is as described by this BNF:
     * <blockquote><pre>
     * {@code
     * Guid                   = <time_low> "-" <time_mid> "-"
     *                          <time_high_and_version> "-"
     *                          <variant_and_sequence> "-"
     *                          <node>
     * time_low               = 4*<hexOctet>
     * time_mid               = 2*<hexOctet>
     * time_high_and_version  = 2*<hexOctet>
     * variant_and_sequence   = 2*<hexOctet>
     * node                   = 6*<hexOctet>
     * hexOctet               = <hexDigit><hexDigit>
     * hexDigit               =
     *       "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
     *       | "a" | "b" | "c" | "d" | "e" | "f"
     *       | "A" | "B" | "C" | "D" | "E" | "F"
     * }</pre></blockquote>
     *
     * @return  A string representation of this {@code Guid}
     */
    @Override
    public String toString() {
        return (digits(mostSigBits >> 32, 8) + "-" +
                digits(mostSigBits >> 16, 4) + "-" +
                digits(mostSigBits, 4) + "-" +
                digits(leastSigBits >> 48, 4) + "-" +
                digits(leastSigBits, 12));
    }

    /** Returns val represented by the specified number of hex digits. */
    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Long.toHexString(hi | (val & (hi - 1))).substring(1);
    }

    /**
     * Returns a hash code for this {@code Guid}.
     *
     * @return  A hash code value for this {@code Guid}
     */
    @Override
    public int hashCode() {
        long hilo = mostSigBits ^ leastSigBits;
        return ((int)(hilo >> 32)) ^ (int) hilo;
    }

    /**
     * Compares this object to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null}, is a {@code Guid}
     * object, has the same variant, and contains the same value, bit for bit,
     * as this {@code Guid}.
     *
     * @param  obj
     *         The object to be compared
     *
     * @return  {@code true} if the objects are the same; {@code false}
     *          otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if ((null == obj) || (obj.getClass() != Guid.class))
            return false;
        Guid id = (Guid)obj;
        return (mostSigBits == id.mostSigBits &&
                leastSigBits == id.leastSigBits);
    }

    // Comparison Operations

    /**
     * Compares this Guid with the specified Guid.
     *
     * <p> The first of two UUIDs is greater than the second if the most
     * significant field in which the UUIDs differ is greater for the first
     * Guid.
     *
     * @param  val
     *         {@code Guid} to which this {@code Guid} is to be compared
     *
     * @return  -1, 0 or 1 as this {@code Guid} is less than, equal to, or
     *          greater than {@code val}
     *
     */
    @Override
    public int compareTo(Guid val) {
        // The ordering is intentionally set up so that the UUIDs
        // can simply be numerically compared as two numbers
        return (this.mostSigBits < val.mostSigBits ? -1 :
                (this.mostSigBits > val.mostSigBits ? 1 :
                 (this.leastSigBits < val.leastSigBits ? -1 :
                  (this.leastSigBits > val.leastSigBits ? 1 :
                   0))));
    }

    public int[] toByteArray() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

