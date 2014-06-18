LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CPP_EXTENSION := .cpp

INCLUDES = -DKOBO_DATA_DIR=\""mnt/sdcard/kobo-deluxe"\"	\
	-DKOBO_SCORE_DIR=\"""\"	\
	-DKOBO_CONFIG_DIR=\""HOME"\"	\
	-DKOBO_CONFIG_FILE=\"".kobodlrc"\"	\
	-DSYSCONF_DIR=\"""\"

LOCAL_CFLAGS := -fexceptions
LOCAL_CPPFLAGS := -fexceptions -fpermissive $(INCLUDES)

LOCAL_MODULE := main

SDL_PATH := ../SDL

KOBODELUXE_PATH := ../KoboDeluxe

LOCAL_C_INCLUDES := $(LOCAL_PATH)/$(SDL_PATH)/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(KOBODELUXE_PATH)

# Add your application source files here...
LOCAL_SRC_FILES := $(SDL_PATH)/src/main/android/SDL_android_main.c

# Add KoboDeluxe main
MY_FILES := $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/*.c)
MY_FILES += $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/*.cpp)
MY_FILES := $(MY_FILES:$(LOCAL_PATH)/%=%)
LOCAL_SRC_FILES += $(MY_FILES)
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(KOBODELUXE_PATH)

# Add KoboDeluxe sound
MY_FILES := $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/sound/*.c)
MY_FILES += $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/sound/*.cpp)
MY_FILES := $(MY_FILES:$(LOCAL_PATH)/%=%)
LOCAL_SRC_FILES += $(MY_FILES)
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(KOBODELUXE_PATH)/sound

# Add KoboDeluxe graphics
MY_FILES := $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/graphics/*.c)
MY_FILES += $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/graphics/*.cpp)
MY_FILES := $(MY_FILES:$(LOCAL_PATH)/%=%)
LOCAL_SRC_FILES += $(MY_FILES)
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(KOBODELUXE_PATH)/graphics

# Add KoboDeluxe eel
MY_FILES := $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/eel/*.c)
MY_FILES += $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/eel/*.cpp)
MY_FILES := $(MY_FILES:$(LOCAL_PATH)/%=%)
LOCAL_SRC_FILES += $(MY_FILES)
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(KOBODELUXE_PATH)/eel

# Add KoboDeluxe data/sfx
MY_FILES := $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/data/sfx/*.c)
MY_FILES += $(wildcard $(LOCAL_PATH)/$(KOBODELUXE_PATH)/data/sfx/*.cpp)
MY_FILES := $(MY_FILES:$(LOCAL_PATH)/%=%)
LOCAL_SRC_FILES += $(MY_FILES)
LOCAL_C_INCLUDES += $(LOCAL_PATH)/$(KOBODELUXE_PATH)/data/sfx

LOCAL_CPP_INCLUDES := $(LOCAL_C_INCLUDES)
LOCAL_SHARED_LIBRARIES := SDL2 SDL2_image

LOCAL_LDLIBS := -lGLESv1_CM -lGLESv2 -llog -lz

include $(BUILD_SHARED_LIBRARY)
