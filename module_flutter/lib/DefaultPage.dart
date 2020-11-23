import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class DefaultPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: null,
      body: Container(
          color: Colors.white,
          child: Center(
            child: Text("DefaultPage"),
          )),
    );
  }
}
