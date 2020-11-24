import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

class FirstPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: null,
        backgroundColor: Colors.white,
        body: Container(
              child: RaisedButton(
                child: Text("jump native page"),
                onPressed: () {
                  FlutterBoost.singleton.open("sample://activity_a",
                      exts: {"data": "hahahahahhahahah"},
                      urlParams: {"key": "bbb"});
                },
              ),
            ),

        );
  }
}
