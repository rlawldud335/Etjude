<template>
  <div class="studio__scene__header">
    <span>#{{ scene.sceneNumber }}. {{ scene.roleName }}</span>
    <div class="recording-btn" :class="{ 'recording-disable-btn': videoState.isRecording }" @click="startRecording">녹화히기
    </div>
  </div>
  <div class="studio__scene">
    <div class="studio__scene-actor-image">
      <div class="actor__profile-frame--script">
        <img class="actor__profile-image" :src="scene.rolePhotoUrl" alt="" />
      </div>
    </div>
    <div class="studio__tab-line-section">
      <ScriptTabSceneLine v-for="(line, index) in scene.lines" :key="index" :lineIdx="index" :line="line"
        :roleName="scene.roleName" @click="changeScriptTime(filterTime(line.lineTimeStamp))">
      </ScriptTabSceneLine>
    </div>
  </div>
</template>

<script>
import ScriptTabSceneLine from "@/components/studio/ScriptTabSceneLine.vue";

export default {
  name: "ScriptTabScene",
  components: { ScriptTabSceneLine },
  props: {
    scene: Object, videoState: Object
  },
  emits: [
    'change-script-time', 'start-recording'
  ],
  setup(props, { emit }) {

    const changeScriptTime = (time) => {
      emit('change-script-time', time);
    }

    const filterTime = (time) => {
      const sptTime = time.split(':');
      const result = parseInt(sptTime[0], 10) * 60 + parseInt(sptTime[1], 10) + (parseInt(sptTime[2], 10) / 10);
      console.log(result);
      return result;
    }

    const startRecording = () => {
      emit("start-recording", props.scene.sceneNumber);
    }

    return { changeScriptTime, filterTime, startRecording };
  },
};
</script>

<style lang="scss">
.studio__scene {
  display: flex;
  flex-direction: row;
  margin: 5px;
  margin-bottom: 15px;
}

.studio__scene-actor-image {
  width: 15%;
  display: flex;
  justify-content: center;
}

.studio__tab-line-section {
  width: 85%;
}

.actor__profile-frame--script {
  margin-top: 12px;
  width: 40px;
  height: 40px;
  border-radius: 70%;
  overflow: hidden;
  justify-content: center;

  img {
    max-width: 40px;
    height: auto;
  }
}

.studio__scene__header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  font-size: 15px;
}

.recording-btn {
  background-color: $bana-pink;
  padding: 7px 12px;
  font-size: 5px;
  border-radius: 5px;
  font-weight: 300;
  color: white;
}

.recording-disable-btn {
  background-color: gray;
}
</style>
