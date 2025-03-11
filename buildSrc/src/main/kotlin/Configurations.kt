object Configurations {

    const val COMPILE_SDK_VERSION = 35
    const val MIN_SDK_VERSION = 27
    const val TARGET_SDK_VERSION = 35

    /**
     * For each release, update these values based on the type of changes:
     *
     * Major Version: For significant or non-backward-compatible changes.
     * Minor Version: For backward-compatible new features or improvements.
     * Patch Version: For small bug fixes or minor updates.
     */

    const val MAJOR_VERSION = 4
    const val MINOR_VERSION = 4
    const val PATCH_VERSION = 0
    const val VERSION_CODE = MAJOR_VERSION * 10000 + MINOR_VERSION * 100 + PATCH_VERSION
    const val VERSION_NAME = "$MAJOR_VERSION.$MINOR_VERSION.$PATCH_VERSION"
}
