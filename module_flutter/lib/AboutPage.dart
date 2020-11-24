import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

typedef GestureTapCallback = void Function();

class AboutPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "About",
          style: TextStyle(color: Colors.black),
        ),
        leading: BackButton(
          color: Colors.black,
          onPressed: () {
            FlutterBoost.singleton.closeByContext(context);
          },
        ),
        elevation: 0,
        backgroundColor: Colors.white,
        brightness: Brightness.light,
      ),
      body: Ink(
        color: Colors.white,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Divider(
              height: 0.1,
              color: Color(0x4D46494E),
            ),
            SizedBox(
              height: 24,
            ),
            SizedBox(
              width: 96,
              height: 96,
              child: Image.asset('assets/image/ic_launcher.png'),
            ),
            SizedBox(
              height: 14,
            ),
            _buildItem("Privacy Policy", () {
              print("Privacy click");
            }),
            _buildItem("Terms of Service", () {
              print("Terms click");
            }),
            Flexible(
              flex: 1,
              child: Container(
                margin: EdgeInsets.fromLTRB(0, 0, 0, 40),
                alignment: Alignment.bottomCenter,
                child: Text(
                  'Copyright © 2020 iMissYo',
                  style: TextStyle(color: Color(0xFF989898), fontSize: 13),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildItem(String text, GestureTapCallback callback) {
    return Column(
      children: [
        InkWell(
          child: Container(
            padding: EdgeInsets.fromLTRB(16, 0, 8, 0),
            height: 56,
            child: Row(
              children: [
                Expanded(
                  child: Container(
                    child: Text(
                      text,
                      style: TextStyle(color: Color(0xFF4C4C4C), fontSize: 14),
                    ),
                  ),
                ),
                Icon(
                  Icons.keyboard_arrow_right,
                  color: Colors.black,
                )
              ],
            ),
          ),
          onTap: () {
            callback();
          },
        ),
        Divider(
          height: 0.1,
          color: Color(0x4D46494E),
        )
      ],
    );
  }
}
