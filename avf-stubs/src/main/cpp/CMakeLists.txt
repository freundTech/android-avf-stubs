cmake_minimum_required(VERSION 3.22.1)

project("hidden-api")

add_library(vm_payload SHARED stub.c)

target_link_libraries(vm_payload log)

target_link_options(vm_payload
    PRIVATE
    -Wl,--version-script,${CMAKE_SOURCE_DIR}/libvm_payload.map.txt
    -Wl,--no-undefined-version
)

set_target_properties(vm_payload
    PROPERTIES
    LINK_DEPENDS ${CMAKE_SOURCE_DIR}/libvm_payload.map.txt
)