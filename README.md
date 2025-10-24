# KET词汇学习应用

为小学生设计的Android原生应用，支持本地存储和艾斯浩宾曲线复习。

## 功能特点

- 📱 Android原生应用
- 🎯 专为小学生设计
- 📚 KET词汇学习
- 🧠 艾斯浩宾曲线复习
- 💾 本地数据存储
- 🔊 Android TTS发音
- 🎨 美观界面设计

## 技术栈

- Kotlin
- Android Jetpack Compose
- Room数据库
- Hilt依赖注入
- MVVM架构

## 构建说明

使用GitHub Actions自动构建APK文件。

## 安装说明

1. 下载APK文件
2. 启用"未知来源"安装
3. 点击APK文件安装

## 使用说明

1. 创建用户（最多3个用户）
2. 选择学习模式
3. 开始学习词汇
4. 系统会根据艾斯浩宾曲线自动安排复习

## 项目结构

```
app/
├── src/main/java/com/ket/vocabulary/
│   ├── core/           # 核心模块
│   ├── feature/        # 功能模块
│   └── ui/            # UI组件
├── src/main/res/       # 资源文件
└── src/main/assets/    # 数据文件
```

## 开发说明

项目使用Android Studio开发，支持Gradle构建。