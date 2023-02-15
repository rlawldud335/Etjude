<template>
  <div class="studio__scene__header">
    <span>#{{ scene.sceneNumber }}. {{ scene.roleName }}</span>
    <RecordingIcon v-if="!videoState.isRecording" @click="startRecording" />
    <DisableRecordingIcon v-if="videoState.isRecording" />
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
import RecordingIcon from "@/assets/icons/recordingIcon.svg";
import DisableRecordingIcon from "@/assets/icons/disableRecodingIcon.svg";

export default {
  name: "ScriptTabScene",
  components: { ScriptTabSceneLine, RecordingIcon, DisableRecordingIcon },
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
  padding: 10px 30px;
  padding-left: 20px;
  font-size: 15px;
}
</style>
