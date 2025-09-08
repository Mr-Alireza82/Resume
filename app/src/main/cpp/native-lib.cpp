#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_sampleproject_features_main_presentation_NativeBridge_stringFromJNI(JNIEnv *env,
                                                                                     jclass clazz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
