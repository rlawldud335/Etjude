CREATE DATABASE  IF NOT EXISTS `unnamed` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `unnamed`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: i8a605.p.ssafy.io    Database: unnamed
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_desc` longtext,
  `role_name` varchar(255) DEFAULT NULL,
  `role_photo_url` varchar(255) DEFAULT NULL,
  `story_id` bigint DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `FKsvfy3dyrt4v8ac82c8pqac21s` (`story_id`),
  CONSTRAINT `FKsvfy3dyrt4v8ac82c8pqac21s` FOREIGN KEY (`story_id`) REFERENCES `story` (`story_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'#소신있게살자 #중졸 #전과자 #단밤사장 무릎 한번을 꿇지 못해 퇴학을 당했고, 원수를 폭행하여 살인미수 전과자가 되었다. 폭행을 했으니 벌을 받는 법, 억울할 것도 후회할 것도 없다. 그것이 새로이의 가치관, 소신. “내 인생 이제 시작이고, 난... 원하는 거 다 이루면서 살 거야.” 중졸, 전과자, 모든 것을 잃고도 새로이는 꺾이지 않는다. 첫사랑 수아가 살고 있다는 이태원에 가게 된 새로이. 다양한 인종, 세계음식, 해외여행을 온 듯한 느낌 있는 건물들. 자유로운 분위기의 이 거리에 새로이는 반해버렸다. 이곳 이태원에서 장사를 시작하자 마음먹는다. 하지만 이곳은 서울의 중심 용산, 그 안에서도 가장 핫한 이태원. 무일푼의 새로이에겐 너무나도 비싼 권리금과 보증금. “쉬울 거라 생각 안했어, 어렵게 하면 되지, 돼, 당연한 거야.” 원양어선, 공장, 막노동 등 갖은 고초를 하며 7년간 번 돈으로 계획대로 이태원에서 단밤 포차를 차린 새로이. 그에게는 너무나도 당연한 일이었다.','박새로이','https://search.pstatic.net/common?type=f&size=206x232&quality=75&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fkeypage%2Fimage%2Fdss%2F60%2F81%2F36%2F06%2F60_11813606_main_image_new_1578540693148.jpg',1),(2,'#소시오패스 #IQ162 #인플루언서 #단밤매니저\n공부면 공부, 운동이면 운동, 다재다능한 천재 이서. 어머니의 남다른 교육 때문이었을까, 이서의 인생관은 확실하다. 명문대를 졸업하고, 대기업에 취직하여, 재벌 남편과 결혼해 성공한 인생을 사는 것. 스스로가 생각해도 따분한 루트.\n“사랑, 인간이 할 수 있는 가장 바보 같은 짓거리야.”\n그렇게 살아왔던 이서 앞에 나타난 한 남자, 자신과 정반대 성향의 새로이. 이서의 똑똑한 머리로는 이해가 되지 않는 상황. 새로이가 보고 싶다, 그가 좋다. 이서는 욕심쟁이다, 원하는 것은 이뤄내야 직성이 풀린다.\n“난 너무 잘났기에... 꿈, 사랑 모두 이룰 수 있어.”\n볼 것 없는 이 남자를, 대단한 남자로 만들겠다 마음먹은 이서. 명문대 입학을 포기하고 단밤의 매니저로 취직한다. 좋아하는 새로이의 머리에는 온통 장가에 대한 복수뿐. 그것이 질투가 난다. 이 빌어먹을 것들, 부숴버리겠다 마음먹는다.','조이서','https://search.pstatic.net/common?type=f&size=210x236&quality=90&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fkeypage%2Fimage%2Fdss%2F60%2F81%2F37%2F68%2F60_11813768_main_image_new_1578541186697.jpg',1),(3,'#소신있게살자 #중졸 #전과자 #단밤사장 무릎 한번을 꿇지 못해 퇴학을 당했고, 원수를 폭행하여 살인미수 전과자가 되었다. 폭행을 했으니 벌을 받는 법, 억울할 것도 후회할 것도 없다. 그것이 새로이의 가치관, 소신. “내 인생 이제 시작이고, 난... 원하는 거 다 이루면서 살 거야.” 중졸, 전과자, 모든 것을 잃고도 새로이는 꺾이지 않는다. 첫사랑 수아가 살고 있다는 이태원에 가게 된 새로이. 다양한 인종, 세계음식, 해외여행을 온 듯한 느낌 있는 건물들. 자유로운 분위기의 이 거리에 새로이는 반해버렸다. 이곳 이태원에서 장사를 시작하자 마음먹는다. 하지만 이곳은 서울의 중심 용산, 그 안에서도 가장 핫한 이태원. 무일푼의 새로이에겐 너무나도 비싼 권리금과 보증금. “쉬울 거라 생각 안했어, 어렵게 하면 되지, 돼, 당연한 거야.” 원양어선, 공장, 막노동 등 갖은 고초를 하며 7년간 번 돈으로 계획대로 이태원에서 단밤 포차를 차린 새로이. 그에게는 너무나도 당연한 일이었다.','박새로이','https://search.pstatic.net/common?type=f&size=206x232&quality=75&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fkeypage%2Fimage%2Fdss%2F60%2F81%2F36%2F06%2F60_11813606_main_image_new_1578540693148.jpg',2),(4,'#소시오패스 #IQ162 #인플루언서 #단밤매니저 공부면 공부, 운동이면 운동, 다재다능한 천재 이서. 어머니의 남다른 교육 때문이었을까, 이서의 인생관은 확실하다. 명문대를 졸업하고, 대기업에 취직하여, 재벌 남편과 결혼해 성공한 인생을 사는 것. 스스로가 생각해도 따분한 루트.  “사랑, 인간이 할 수 있는 가장 바보 같은 짓거리야.”  그렇게 살아왔던 이서 앞에 나타난 한 남자, 자신과 정반대 성향의 새로이. 이서의 똑똑한 머리로는 이해가 되지 않는 상황. 새로이가 보고 싶다, 그가 좋다. 이서는 욕심쟁이다, 원하는 것은 이뤄내야 직성이 풀린다.  “난 너무 잘났기에... 꿈, 사랑 모두 이룰 수 있어.”  볼 것 없는 이 남자를, 대단한 남자로 만들겠다 마음먹은 이서. 명문대 입학을 포기하고 단밤의 매니저로 취직한다. 좋아하는 새로이의 머리에는 온통 장가에 대한 복수뿐. 그것이 질투가 난다. 이 빌어먹을 것들, 부숴버리겠다 마음먹는다.','조이서','https://search.pstatic.net/common?type=f&size=210x236&quality=90&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fkeypage%2Fimage%2Fdss%2F60%2F81%2F37%2F68%2F60_11813768_main_image_new_1578541186697.jpg',2),(5,'상대를 꿰뚫어 보는 눈빛과 한 치의 빈틈도 허락지 않는 꼿꼿한 인상. 나이답지 않게 다부진 몸. 정미소에서 시작해 순양을 재계 1위에 올려놓았다. 그의 ‘3 心’ 덕분에- 욕심, 의심, 변심. 이제 그는 순양을 잘 이끌 후계자가 필요하다. 하지만 눈에 차지 않는 삼남매만 있을 뿐. 그런데 순양 家를 떠났던 막내가 돌아왔다. 제 아들 진도준과 함께. 그는 한눈에 알아봤다. 막내손자가 자신을 닮았다는 걸- 승부근성, 결단력, 가차 없는 냉혹함까지.','진양철','https://fs.jtbc.co.kr/prog/drama/rebornrich/Img/site/ProgInfo/202211100323537197.jpg',4),(6,'금수저. 있는 집 순양 家의 막내아들. 명석한 두뇌, 집요한 승부 근성. 마치 미래에 대해 모든 걸 알고 있는 듯 보이는 비상한 통찰력. 가차 없는 냉정함. 충성을 다 했지만 순양 家로부터 죽임을 당한 윤현우, 1987년 진양철 회장의 막내 손자, 진도준으로 다시 깨어난다. 순양 家의 머슴 윤현우는 억울하게 죽임을 당했지만 순양 家의 막내 진도준은 죽지 않고 반드시 순양제국의 왕좌를 뺏을 것이다. 자신을 죽인 순양 일가로부터..','진도준','https://fs.jtbc.co.kr/prog/drama/rebornrich/Img/site/ProgInfo/202211100323211259.jpg',4),(7,'#대놓고취집주의자 #꿈은동화같은가정의현모양처 #원석이맞춤여우  20대 시절 귀엽고 사랑스러운 미모로 누구에게나 인기 있는 타입이었지만, 호랑의 목표는 확실했다. ‘자수성가형 성공남’과 결혼해서 ‘전업주부’가 되겠다.  ‘금수저는 안돼. 부모한테 물려받은 재산에는 시집살이가 포함되어있는 법이거든.’ 라고 조목조목 설명할 때 호랑은 불과 열여덟이었다.','양호랑','https://search.pstatic.net/common?type=f&size=206x232&quality=75&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fkeypage%2Fimage%2Fdss%2F60%2F36%2F29%2F06%2F60_6362906_main_image_new_1506317217054.jpg',3),(8,'#호랑의남자 #귀여운공대형순정남 #스티브잡스를꿈꾸는 #원석중의원석 #결혼말고연애앱최고기술경영자(CTO)  천재의 반대말이 바보가 아님을 입증한 천재형 바보다. 한때 심심해서 만든 웹사이트로 S대 공칠(07)잡스로 불리며 이름을 날렸다. 그렇게 프로그래밍밖에 모르던 전형적인 공대남에게 한 여자가 나타났으니, 바로 호랑이다.','심원석','https://search.pstatic.net/common?type=f&size=206x232&quality=75&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fkeypage%2Fimage%2Fdss%2F60%2F36%2F29%2F28%2F60_6362928_main_image_new_1506316843501.jpg',3),(9,'고등학교 펜싱부에서 국가대표. 펜싱 신동에서 4000만의 안티가 되는 금메달리스트이다. 에너지와 패기 넘치는 성격으로, 목표를 향해 굳건하게 달려가는 인물이다. IMF 사태로 부유한 집안 도련님에서 가장이 된 한 남자와 엮이면서 사랑과 우정, 꿈을 고민한다.','나희도','https://search.pstatic.net/common?type=f&size=210x236&quality=90&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20220209_4%2F16443915107775qrgl_JPEG%2F60_main_image_new_1644391510755.jpg',5),(10,'UBS 스포츠 기자. 스물둘에 방송국 기자가 된 인물로 잠복 취재할 땐 화장품 외판원이 됐다가 경호원이 되기도 하고 아시안게임이 열려 스포츠기자도 된다. 아시안게임 펜싱 도중 금메달리스트 나희도를 만난다.  부유한 집안 출신이였으나, IMF 시대의 영향으로 집안이 망해서 가족은 뿔뿔이 흩어지게 되고 서민동네로 혼자 이사와 낡아 빠진 셋방에서 살면서 온갖 알바 중이다. 배달하는 신문을 담 넘어 던져 넣었다가, 나희도 집 마당에 있는 오줌싸개 아기 동상을 신문으로 맞췄다 이때 일로 나희도와의 인연을 악연으로 시작한다. 그러다 책 대여점에서 알바하다 나희도와 만나 책 손상과 풀하우스 신간 문제로 다툰다.','백이진','https://search.pstatic.net/common?type=f&size=210x236&quality=90&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20220209_276%2F1644391619528d2kwR_JPEG%2F60_main_image_new_1644391619467.jpg',5),(11,'9	고등학교 펜싱부에서 국가대표. 펜싱 신동에서 4000만의 안티가 되는 금메달리스트이다. 에너지와 패기 넘치는 성격으로, 목표를 향해 굳건하게 달려가는 인물이다. IMF 사태로 부유한 집안 도련님에서 가장이 된 한 남자와 엮이면서 사랑과 우정, 꿈을 고민한다.','나희도','https://search.pstatic.net/common?type=f&size=210x236&quality=90&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20220209_4%2F16443915107775qrgl_JPEG%2F60_main_image_new_1644391510755.jpg',6),(12,'UBS 스포츠 기자. 스물둘에 방송국 기자가 된 인물로 잠복 취재할 땐 화장품 외판원이 됐다가 경호원이 되기도 하고 아시안게임이 열려 스포츠기자도 된다. 아시안게임 펜싱 도중 금메달리스트 나희도를 만난다.  부유한 집안 출신이였으나, IMF 시대의 영향으로 집안이 망해서 가족은 뿔뿔이 흩어지게 되고 서민동네로 혼자 이사와 낡아 빠진 셋방에서 살면서 온갖 알바 중이다. 배달하는 신문을 담 넘어 던져 넣었다가, 나희도 집 마당에 있는 오줌싸개 아기 동상을 신문으로 맞췄다 이때 일로 나희도와의 인연을 악연으로 시작한다. 그러다 책 대여점에서 알바하다 나희도와 만나 책 손상과 풀하우스 신간 문제로 다툰다.','백이진','https://search.pstatic.net/common?type=f&size=210x236&quality=90&direct=true&src=https%3A%2F%2Fcsearch-phinf.pstatic.net%2F20220209_276%2F1644391619528d2kwR_JPEG%2F60_main_image_new_1644391619467.jpg',6),(13,'10년의 시간이 지난 지금의 최웅은 많은 게 변했다.  그늘에 누워 낮잠 자는 평온한 삶을 꿈꿨지만, 지금은 밤에도 잠을 자지 못하는  영혼 없는 삶을 살고 있다.  아티스트로서 최고의 인기와 성공을 이루어 내고 있지만, 최웅의 눈에는 어쩐지 공허함만 가득하다.  그리고 연수가 다시 찾아왔다.  처음 만났던 것처럼 예고도 없이.  그렇게 싸웠던 시간들이 아직 부족했던 건지 아직 할 말이 남은 건지.  하지만 이젠 예전의 최웅이 아니다. ','최웅','https://img2.sbs.co.kr/img/sbs_cms/WE/2021/11/17/Arj1637110854076-666-968.jpg',7),(14,'가난하기 너무 싫은 이유는 내가 남에게 무언가를 베풀 수가 없다는 거다.  특히 날 때부터 따라다닌 가난은 클 수록 친구와 밥 한끼, 커피 한 잔 하는 것도 꺼리게 만든다.  그래서 그런 것들에 관심이 없는 척, 나만 신경 쓰는 척.  그게 연수가 살아온 방법이었다.','국연수','https://img2.sbs.co.kr/img/sbs_cms/WE/2021/11/17/0Ks1637110936935-666-968.jpg',7);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17  0:17:59
