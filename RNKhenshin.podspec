require "json"

package = JSON.parse(File.read(File.join(__dir__, ".", ".", "package.json")))
version = package['version']

source = { :git => "https://github.com/khipu/react-native-khenshin.git", :tag => "master" }
if version == '1000.0.0'
  # This is an unpublished version, use the latest commit hash of the react-native repo, which we’re presumably in.
  source[:commit] = `git rev-parse HEAD`.strip
else
  source[:tag] = "#{version}"
end

Pod::Spec.new do |s|
  s.name                   = "RNKhenshin"
  s.version                = version
  s.summary                = package["description"]
  s.homepage               = package["homepage"]
  s.documentation_url      = package['documentation']
  s.license                = package["license"]
  s.author                 = package["author"]
  s.authors                = package["author"]
  s.platforms              = { :ios => "9.0" }
  s.compiler_flags         = '-Wno-nullability-completeness'
  s.source                 = source
  s.source_files           = "ios/**/*.{h,m}"
  s.preserve_paths         = "package.json", "ios", "index.js"
  s.header_dir             = "RNKhenshin"
  s.pod_target_xcconfig    = {
                               "USE_HEADERMAP" => "YES",
                               "CLANG_CXX_LANGUAGE_STANDARD" => "c++14",
                               "HEADER_SEARCH_PATHS" => "\"$(PODS_ROOT)/RNKhenshin\""
                             }
  s.dependency "khenshin", '1.611'
  s.dependency "React-Core"
end
