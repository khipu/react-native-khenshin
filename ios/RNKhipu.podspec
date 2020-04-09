
Pod::Spec.new do |s|
  s.name         = "RNKhipu"
  s.version      = "1.0.0"
  s.summary      = "RNKhipu"
  s.description  = "Wrapper react-native para la tecnología browser2app de khipu"
  s.homepage     = "https://github.com/khipu/react-native-khenshin"
  s.license      = "MIT"
  s.author       = { "Khipu SpA" => "support@khipu.com" }
  s.platform     = :ios, "9.0"
  s.source       = { :git => "https://github.com/khipu/react-native-khenshin.git", :tag => "master" }
  s.source_files  = "RNKhipu/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

