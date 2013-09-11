package net.larry1123.util.config;

import net.canarymod.api.world.World;
import net.larry1123.util.logger.EELogger;
import net.visualillusionsent.utils.PropertiesFile;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ConfigFile {

    private final PropertiesFile propertiesfile;
    private final ConfigBase config;

    public ConfigFile(ConfigBase config, PropertiesFile propertiesfile) {
        this.config = config;
        this.propertiesfile = propertiesfile;
        setupfile();
    }

    public ConfigFile(ConfigBase config, String plugin) {
        this.config = config;
        String filepath = "config" + File.separatorChar + plugin + File.separatorChar + plugin + ".cfg";
        propertiesfile = new PropertiesFile(filepath);
        setupfile();
    }

    public ConfigFile(ConfigBase config, String plugin, String module) {
        this.config = config;
        String filepath = "config" + File.separatorChar + plugin + File.separatorChar + plugin + "." + module + ".cfg";
        propertiesfile = new PropertiesFile(filepath);
        setupfile();
    }

    public ConfigFile(ConfigBase config, String plugin, World world) {
        this.config = config;
        String filepath = "config" + File.separatorChar + plugin + File.separatorChar + "worlds" + File.separatorChar + world.getFqName() + File.separatorChar + plugin + ".cfg";
        propertiesfile = new PropertiesFile(filepath);
        setupfile();
    }

    public ConfigFile(ConfigBase config, String plugin, String module, World world) {
        this.config = config;
        String filepath = "config" + File.separatorChar + plugin + File.separatorChar + "worlds" + File.separatorChar + world.getFqName() + File.separatorChar + plugin + "." + module + ".cfg";
        propertiesfile = new PropertiesFile(filepath);
        setupfile();
    }

    public PropertiesFile getPropFile() {
        return propertiesfile;
    }

    public void reload() {
        propertiesfile.reload();
        setupfile();
    }

    public void save() {
        Field[] fields = config.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ConfigFeild.class)) {
                Type fieldtype = field.getGenericType();
                ConfigFeild ano = field.getAnnotation(ConfigFeild.class);
                boolean hasspacer = false;
                if (!ano.spacer().equals("")) {
                    hasspacer = true;
                }
                Object value = null;
                boolean hasvalue = false;
                try {
                    value = field.get(config);
                    hasvalue = true;
                } catch (IllegalArgumentException e1) {
                    hasvalue = false;
                } catch (IllegalAccessException e1) {
                    hasvalue = false;
                } finally {
                    if (value == null) {
                        continue;
                    } else {
                        hasvalue = true;
                    }
                }

                String fieldName;
                if (ano.name().equals("")) {
                    fieldName = field.getName();
                } else {
                    fieldName = ano.name();
                }

                Object result = null;

                byte[] barray;
                double[] darray;
                float[] farray;
                int[] iarray;
                long[] larray;
                short[] sarray;

                switch (TypeValues.getFromType(fieldtype)) {

                    // Boolean
                    case BOOLEANWRAP:
                    case BOOLEAN:
                        result = propertiesfile.getBoolean(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setBoolean(fieldName, (Boolean) value);
                        }
                        break;

                    // Byte
                    case BYTEWRAP:
                    case BYTE:
                        result = propertiesfile.getByte(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setByte(fieldName, (Byte) value);
                        }
                        break;

                    case BYTEWRAPARRAY:
                        value = ArrayUtils.toPrimitive((Byte[]) value);
                    case BYTEARRAY:
                        barray = (byte[]) value;
                        if (hasspacer) {
                            result = propertiesfile.getByteArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getByteArray(fieldName);
                        }
                        if (!Arrays.equals((byte[]) result, barray)) {
                            if (hasspacer) {
                                propertiesfile.setByteArray(fieldName, ano.spacer(), barray);
                            } else {
                                propertiesfile.setByteArray(fieldName, barray);
                            }
                        }
                        break;

                    // Character
                    case CHARACTERWRAP:
                    case CHARACTER:
                        result = propertiesfile.getCharacter(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setCharacter(fieldName, (Character) value);
                        }
                        break;

                    // Double
                    case DOUBLEWRAP:
                    case DOUBLE:
                        result = propertiesfile.getDouble(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setDouble(fieldName, (Double) value);
                        }
                        break;

                    case DOUBLEWRAPARRAY:
                        value = ArrayUtils.toPrimitive((Double[]) value);
                    case DOUBLEARRAY:
                        darray = (double[]) value;
                        if (hasspacer) {
                            result = propertiesfile.getDoubleArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getDoubleArray(fieldName);
                        }
                        if (!Arrays.equals((double[]) result, darray)) {
                            if (hasspacer) {
                                propertiesfile.setDoubleArray(fieldName, ano.spacer(), darray);
                            } else {
                                propertiesfile.setDoubleArray(fieldName, darray);
                            }
                        }
                        break;

                    // Float
                    case FLOATWRAP:
                    case FLOAT:
                        result = propertiesfile.getFloat(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setFloat(fieldName, (Float) value);
                        }
                        break;

                    case FLOATWRAPARRAY:
                        value = ArrayUtils.toPrimitive((Float[]) value);
                    case FLOATARRAY:
                        farray = (float[]) value;
                        if (hasspacer) {
                            result = propertiesfile.getFloatArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getFloatArray(fieldName);
                        }
                        if (!Arrays.equals((float[]) result, farray)) {
                            if (hasspacer) {
                                propertiesfile.setFloatArray(fieldName, ano.spacer(), farray);
                            } else {
                                propertiesfile.setFloatArray(fieldName, farray);
                            }
                        }
                        break;

                    // Integer
                    case INTEGERWRAP:
                    case INTEGER:
                        result = propertiesfile.getInt(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setInt(fieldName, (Integer) value);
                        }
                        break;

                    case INTEGERWRAPARRAY:
                        value = ArrayUtils.toPrimitive((Integer[]) value);
                    case INTEGERARRAY:
                        iarray = (int[]) value;
                        if (hasspacer) {
                            result = propertiesfile.getIntArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getIntArray(fieldName);
                        }
                        if (!Arrays.equals((int[]) result, iarray)) {
                            if (hasspacer) {
                                propertiesfile.setIntArray(fieldName, ano.spacer(), iarray);
                            } else {
                                propertiesfile.setIntArray(fieldName, iarray);
                            }
                        }
                        break;

                    // Long
                    case LONGWRAP:
                    case LONG:
                        result = propertiesfile.getLong(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setLong(fieldName, (Long) value);
                        }
                        break;

                    case LONGWRAPARRAY:
                        value = ArrayUtils.toPrimitive((Long[]) value);
                    case LONGArray:
                        larray = (long[]) value;
                        if (hasspacer) {
                            result = propertiesfile.getLongArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getLongArray(fieldName);
                        }
                        if (!Arrays.equals((long[]) result, larray)) {
                            if (hasspacer) {
                                propertiesfile.setLongArray(fieldName, ano.spacer(), larray);
                            } else {
                                propertiesfile.setLongArray(fieldName, larray);
                            }
                        }
                        break;

                    // Short
                    case SHORTWRAP:
                    case SHORT:
                        result = propertiesfile.getShort(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setShort(fieldName, (Short) value);
                        }
                        break;

                    case SHORTWRAPARRAY:
                        value = ArrayUtils.toPrimitive((Short[]) value);
                    case SHORTARRAY:
                        sarray = (short[]) value;
                        if (hasspacer) {
                            result = propertiesfile.getShortArray(fieldName, ano.spacer());
                        } else {
                            result = propertiesfile.getShortArray(fieldName);
                        }
                        if (!Arrays.equals((short[]) result, sarray)) {
                            if (hasspacer) {
                                propertiesfile.setShortArray(fieldName, ano.spacer(), sarray);
                            } else {
                                propertiesfile.setShortArray(fieldName, sarray);
                            }
                        }
                        break;

                    // String
                    case STRING:
                        result = propertiesfile.getString(fieldName);
                        if (!result.equals(value)) {
                            propertiesfile.setString(fieldName, (String) value);
                        }
                        break;

                    case STRINGARRAY:
                        if (hasvalue) {
                            if (hasspacer) {
                                result = propertiesfile.getStringArray(fieldName, ano.spacer(), (String[]) value);
                            } else {
                                result = propertiesfile.getStringArray(fieldName, (String[]) value);
                            }
                        } else {
                            if (hasspacer) {
                                result = propertiesfile.getStringArray(fieldName, ano.spacer());
                            } else {
                                result = propertiesfile.getStringArray(fieldName);
                            }
                        }
                        if (!Arrays.equals((String[]) result, (String[]) value)) {
                            if (hasspacer) {
                                propertiesfile.setStringArray(fieldName, ano.spacer(), (String[]) value);
                            } else {
                                propertiesfile.setStringArray(fieldName, (String[]) value);
                            }
                        }
                        break;

                    default:
                        EELogger.getLogger("UtilConfigSetUp").warning("Unable to Prosses Type:" + fieldtype.toString());
                        break;
                }
            }
        }
        propertiesfile.save();
    }

    private void setupfile() {
        Field[] fields = config.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ConfigFeild.class)) {
                Type fieldtype = field.getGenericType();
                ConfigFeild ano = field.getAnnotation(ConfigFeild.class);
                boolean hasspacer = false;
                if (!ano.spacer().equals("")) {
                    hasspacer = true;
                }
                Object defaultValue = null;
                boolean hasvalue = false;
                try {
                    defaultValue = field.get(config);
                    hasvalue = true;
                } catch (IllegalArgumentException e1) {
                    hasvalue = false;
                } catch (IllegalAccessException e1) {
                    hasvalue = false;
                } finally {
                    hasvalue = defaultValue != null;
                }

                String fieldName;
                if (ano.name().equals("")) {
                    fieldName = field.getName();
                } else {
                    fieldName = ano.name();
                }

                Object result = null;

                byte[] barray;
                double[] darray;
                float[] farray;
                int[] iarray;
                long[] larray;
                short[] sarray;

                switch (TypeValues.getFromType(fieldtype)) {

                    // Boolean
                    case BOOLEANWRAP:
                    case BOOLEAN:
                        if (hasvalue) {
                            result = propertiesfile.getBoolean(fieldName, (Boolean) defaultValue);
                        } else {
                            result = propertiesfile.getBoolean(fieldName);
                        }
                        break;

                    // Byte
                    case BYTEWRAP:
                    case BYTE:
                        if (hasvalue) {
                            result = propertiesfile.getByte(fieldName, (Byte) defaultValue);
                        } else {
                            result = propertiesfile.getByte(fieldName);
                        }
                        break;

                    case BYTEWRAPARRAY:
                        defaultValue = ArrayUtils.toPrimitive((Byte[]) defaultValue);
                    case BYTEARRAY:
                        barray = (byte[]) defaultValue;
                        if (hasvalue) {
                            if (hasspacer) {
                                result = propertiesfile.getByteArray(fieldName, ano.spacer(), barray);
                            } else {
                                result = propertiesfile.getByteArray(fieldName, barray);
                            }
                        } else {
                            if (hasspacer) {
                                result = propertiesfile.getByteArray(fieldName, ano.spacer());
                            } else {
                                result = propertiesfile.getByteArray(fieldName);
                            }
                        }
                        break;

                    // Character
                    case CHARACTERWRAP:
                    case CHARACTER:
                        if (hasvalue) {
                            result = propertiesfile.getCharacter(fieldName, (Character) defaultValue);
                        } else {
                            result = propertiesfile.getCharacter(fieldName);
                        }
                        break;

                    // Double
                    case DOUBLEWRAP:
                    case DOUBLE:
                        if (hasvalue) {
                            result = propertiesfile.getDouble(fieldName, (Double) defaultValue);
                        } else {
                            result = propertiesfile.getDouble(fieldName);
                        }
                        break;

                    case DOUBLEWRAPARRAY:
                        defaultValue = ArrayUtils.toPrimitive((Double[]) defaultValue);
                    case DOUBLEARRAY:
                        darray = (double[]) defaultValue;
                        if (hasvalue) {
                            if (hasspacer) {
                                result = propertiesfile.getDoubleArray(fieldName, ano.spacer(), darray);
                            } else {
                                result = propertiesfile.getDoubleArray(fieldName, darray);
                            }
                        } else {
                            if (hasspacer) {
                                result = propertiesfile.getDoubleArray(fieldName, ano.spacer());
                            } else {
                                result = propertiesfile.getDoubleArray(fieldName);
                            }
                        }
                        break;

                    // Float
                    case FLOATWRAP:
                    case FLOAT:
                        if (hasvalue) {
                            result = propertiesfile.getFloat(fieldName, (Float) defaultValue);
                        } else {
                            result = propertiesfile.getFloat(fieldName);
                        }
                        break;

                    case FLOATWRAPARRAY:
                        defaultValue = ArrayUtils.toPrimitive((Float[]) defaultValue);
                    case FLOATARRAY:
                        farray = (float[]) defaultValue;
                        if (hasvalue) {
                            if (hasspacer) {
                                result = propertiesfile.getFloatArray(fieldName, ano.spacer(), farray);
                            } else {
                                result = propertiesfile.getFloatArray(fieldName, farray);
                            }
                        } else {
                            if (hasspacer) {
                                result = propertiesfile.getFloatArray(fieldName, ano.spacer());
                            } else {
                                result = propertiesfile.getFloatArray(fieldName);
                            }
                        }
                        break;

                    // Integer
                    case INTEGERWRAP:
                    case INTEGER:
                        if (hasvalue) {
                            result = propertiesfile.getInt(fieldName, (Integer) defaultValue);
                        } else {
                            result = propertiesfile.getInt(fieldName);
                        }
                        break;

                    case INTEGERWRAPARRAY:
                        defaultValue = ArrayUtils.toPrimitive((Integer[]) defaultValue);
                    case INTEGERARRAY:
                        iarray = (int[]) defaultValue;
                        if (hasvalue) {
                            if (hasspacer) {
                                result = propertiesfile.getIntArray(fieldName, ano.spacer(), iarray);
                            } else {
                                result = propertiesfile.getIntArray(fieldName, iarray);
                            }
                        } else {
                            if (hasspacer) {
                                result = propertiesfile.getIntArray(fieldName, ano.spacer());
                            } else {
                                result = propertiesfile.getIntArray(fieldName);
                            }
                        }
                        break;

                    // Long
                    case LONGWRAP:
                    case LONG:
                        if (hasvalue) {
                            result = propertiesfile.getLong(fieldName, (Long) defaultValue);
                        } else {
                            result = propertiesfile.getLong(fieldName);
                        }
                        break;

                    case LONGWRAPARRAY:
                        defaultValue = ArrayUtils.toPrimitive((Long[]) defaultValue);
                    case LONGArray:
                        larray = (long[]) defaultValue;
                        if (hasvalue) {
                            if (hasspacer) {
                                result = propertiesfile.getLongArray(fieldName, ano.spacer(), larray);
                            } else {
                                result = propertiesfile.getLongArray(fieldName, larray);
                            }
                        } else {
                            if (hasspacer) {
                                result = propertiesfile.getLongArray(fieldName, ano.spacer());
                            } else {
                                result = propertiesfile.getLongArray(fieldName);
                            }
                        }
                        break;

                    // Short
                    case SHORTWRAP:
                    case SHORT:
                        if (hasvalue) {
                            result = propertiesfile.getShort(fieldName, (Short) defaultValue);
                        } else {
                            result = propertiesfile.getShort(fieldName);
                        }
                        break;

                    case SHORTWRAPARRAY:
                        defaultValue = ArrayUtils.toPrimitive((Short[]) defaultValue);
                    case SHORTARRAY:
                        sarray = (short[]) defaultValue;
                        if (hasvalue) {
                            if (hasspacer) {
                                result = propertiesfile.getShortArray(fieldName, ano.spacer(), sarray);
                            } else {
                                result = propertiesfile.getShortArray(fieldName, sarray);
                            }
                        } else {
                            if (hasspacer) {
                                result = propertiesfile.getShortArray(fieldName, ano.spacer());
                            } else {
                                result = propertiesfile.getShortArray(fieldName);
                            }
                        }
                        break;

                    // String
                    case STRING:
                        if (hasvalue) {
                            result = propertiesfile.getString(fieldName, (String) defaultValue);
                        } else {
                            result = propertiesfile.getString(fieldName);
                        }
                        break;

                    case STRINGARRAY:
                        if (hasvalue) {
                            if (hasspacer) {
                                result = propertiesfile.getStringArray(fieldName, ano.spacer(), (String[]) defaultValue);
                            } else {
                                result = propertiesfile.getStringArray(fieldName, (String[]) defaultValue);
                            }
                        } else {
                            if (hasspacer) {
                                result = propertiesfile.getStringArray(fieldName, ano.spacer());
                            } else {
                                result = propertiesfile.getStringArray(fieldName);
                            }
                        }
                        break;

                    default:
                        result = null;
                        EELogger.getLogger("UtilConfigSetUp").warning("Unable to Prosses Type:" + fieldtype.toString());
                        break;
                }

                if (result != null) {
                    if ((ano.comments().length == 1 ? !ano.comments()[0].equals("") : true)) {
                        propertiesfile.setComments(fieldName, ano.comments());
                    } else {
                        propertiesfile.removeAllCommentsFromKey(fieldName);
                    }
                    try {
                        field.set(config, result);
                    } catch (IllegalArgumentException e) {
                        // Should Work
                    } catch (IllegalAccessException e) {
                        // Should Work
                    }
                }
            }
        }
        propertiesfile.save();
    }

}
